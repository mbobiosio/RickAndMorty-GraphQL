package com.mbobiosio.rickandmorty.data.remote.model

data class CharactersResult(
    val info: Info,
    val results: List<CharacterModel>
)
