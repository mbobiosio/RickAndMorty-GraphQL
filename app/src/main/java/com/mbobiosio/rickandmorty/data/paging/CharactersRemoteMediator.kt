package com.mbobiosio.rickandmorty.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.mbobiosio.rickandmorty.GetCharactersQuery
import com.mbobiosio.rickandmorty.data.locale.AppDatabase
import com.mbobiosio.rickandmorty.data.locale.entity.RemoteKeyEntity
import com.mbobiosio.rickandmorty.data.mappers.mapToDomainModel
import com.mbobiosio.rickandmorty.data.remote.model.CharacterModel
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CharactersRemoteMediator(
    private val service: ApolloClient,
    private val db: AppDatabase
) : RemoteMediator<Int, CharacterModel>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterModel>
    ): MediatorResult {
        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {

            val response = service.query(GetCharactersQuery(Optional.presentIfNotNull(page))).execute()

            val isEndOfList = response.data?.characters?.info?.next == null || response.toString()
                .contains("error") || response.data?.characters?.results!!.isEmpty()
            val results = response.data?.characters?.results!!

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.characterDao.deleteCharacters()
                    db.remoteKeyDao.deleteAll()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = results.map {
                    RemoteKeyEntity(
                        it!!.id!!,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }

                when {
                    response.hasErrors().not() -> {
                        db.remoteKeyDao.insertAll(keys)

                        db.characterDao.insertCharacters(
                            response.data?.characters!!.results!!.map {
                                it!!.mapToDomainModel()
                            }
                        )
                    }
                }
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, CharacterModel>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
            }
            else -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterModel>
    ): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                db.remoteKeyDao.remoteKeysCharacterId(repoId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, CharacterModel>): RemoteKeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { character -> db.remoteKeyDao.remoteKeysCharacterId(character.id) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, CharacterModel>): RemoteKeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { character -> db.remoteKeyDao.remoteKeysCharacterId(character.id) }
    }
}
