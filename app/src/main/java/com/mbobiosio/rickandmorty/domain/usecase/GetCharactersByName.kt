package com.mbobiosio.rickandmorty.domain.usecase

import androidx.paging.PagingData
import com.mbobiosio.rickandmorty.domain.model.Character
import com.mbobiosio.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersByName(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(characterName: String): Flow<PagingData<Character>> =
        characterRepository.getCharactersByName(characterName)
}
