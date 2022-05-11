package com.mbobiosio.rickandmorty.data.remote.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mbobiosio.rickandmorty.domain.model.Character
import kotlinx.parcelize.Parcelize

@Entity(tableName = "characters")
@Parcelize
data class CharacterModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val image: String,
    val species: String,
    val type: String,
    val gender: String,
    val status: String,
    val origin: Origin,
    val location: Location,
    val episode: List<Episode>,
    val created: String
) : Parcelable {
    fun toCharacter() = Character(
        id = id,
        name = name,
        image = image,
        species = species,
        type = type,
        gender = gender,
        status = status,
        origin = origin,
        location = location,
        created = created,
    )
}
