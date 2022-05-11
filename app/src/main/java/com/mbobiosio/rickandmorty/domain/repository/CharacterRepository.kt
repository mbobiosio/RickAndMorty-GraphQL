package com.mbobiosio.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.mbobiosio.rickandmorty.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharactersByName(characterName: String): Flow<PagingData<Character>>
}
