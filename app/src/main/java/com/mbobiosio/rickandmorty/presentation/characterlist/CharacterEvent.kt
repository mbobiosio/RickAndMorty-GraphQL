package com.mbobiosio.rickandmorty.presentation.characterlist

sealed class CharacterEvent {
    data class GetAllCharactersByName(val characterName: String) : CharacterEvent()
}
