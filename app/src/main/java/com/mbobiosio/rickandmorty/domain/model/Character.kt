package com.mbobiosio.rickandmorty.domain.model

import android.os.Parcelable
import com.mbobiosio.rickandmorty.data.remote.model.Location
import com.mbobiosio.rickandmorty.data.remote.model.Origin
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val gender: String,
    val id: String,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val origin: Origin,
    val location: Location,
    val created: String
) : Parcelable
