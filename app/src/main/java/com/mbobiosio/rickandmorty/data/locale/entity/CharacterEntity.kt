package com.mbobiosio.rickandmorty.data.locale.entity

import androidx.room.PrimaryKey

data class CharacterEntity(
    @PrimaryKey val id: String,
    val gender: String,
    val image: String,
    val location: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String
)
