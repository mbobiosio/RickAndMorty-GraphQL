package com.mbobiosio.rickandmorty.data.repository

import androidx.paging.* // ktlint-disable no-wildcard-imports
import com.apollographql.apollo3.ApolloClient
import com.mbobiosio.rickandmorty.data.locale.AppDatabase
import com.mbobiosio.rickandmorty.data.paging.CharactersRemoteMediator
import com.mbobiosio.rickandmorty.domain.model.Character
import com.mbobiosio.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class CharacterRepositoryImpl(
    private val service: ApolloClient,
    private val db: AppDatabase
) : CharacterRepository {
    override fun getCharactersByName(characterName: String): Flow<PagingData<Character>> {
        val pagingSourceFactory = { db.characterDao.getCharactersByName(characterName) }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true,
            ),
            remoteMediator = CharactersRemoteMediator(
                service,
                db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { data ->
            data.map {
                it.toCharacter()
            }
        }
    }
}
