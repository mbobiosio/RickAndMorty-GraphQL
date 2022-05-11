package com.mbobiosio.rickandmorty.data.locale.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbobiosio.rickandmorty.data.remote.model.CharacterModel

@Dao
interface CharacterDao {

    @Query("Select * from characters where name LIKE '%' || :characterName || '%'")
    fun getCharactersByName(characterName: String): PagingSource<Int, CharacterModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characterModel: List<CharacterModel>)

    @Query("DELETE FROM characters")
    suspend fun deleteCharacters()
}
