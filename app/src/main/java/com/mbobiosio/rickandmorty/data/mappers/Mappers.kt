package com.mbobiosio.rickandmorty.data.mappers

import com.mbobiosio.rickandmorty.GetCharactersQuery
import com.mbobiosio.rickandmorty.data.remote.model.* // ktlint-disable no-wildcard-imports

fun GetCharactersQuery.Info.mapToDomainModel() =
    Info(
        count ?: 0,
        pages ?: 0,
        next ?: 0,
        prev ?: 0
    )

fun GetCharactersQuery.Episode.mapToDomainModel() =
    Episode(
        id ?: "",
        name ?: ""
    )

fun GetCharactersQuery.Location.mapToDomainModel() =
    Location(
        id ?: "",
        name ?: ""
    )

fun GetCharactersQuery.Origin.mapToDomainModel() =
    Origin(
        id ?: "",
        name ?: ""
    )

fun GetCharactersQuery.Result.mapToDomainModel() = CharacterModel(
    id = id ?: "",
    name = name ?: "",
    image = image ?: "",
    species = species ?: "",
    type = type ?: "",
    gender = gender ?: "",
    status = status ?: "",
    origin = origin?.mapToDomainModel() ?: Origin(),
    location = location?.mapToDomainModel() ?: Location(),
    episode = episode.map {
        it!!.mapToDomainModel()
    },
    created = created ?: "",
)

fun GetCharactersQuery.Characters.mapToDomainModel() = CharactersResult(
    info = info?.mapToDomainModel() ?: Info(),
    results = results?.map {
        it!!.mapToDomainModel()
    } ?: emptyList()
)
