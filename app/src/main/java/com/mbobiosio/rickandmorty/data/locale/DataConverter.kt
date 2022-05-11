package com.mbobiosio.rickandmorty.data.locale

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mbobiosio.rickandmorty.data.remote.model.CharacterModel
import com.mbobiosio.rickandmorty.data.remote.model.Episode
import com.mbobiosio.rickandmorty.data.remote.model.Location
import com.mbobiosio.rickandmorty.data.remote.model.Origin
import com.mbobiosio.rickandmorty.utils.jsonConverter
import java.io.Serializable

class DataConverter : Serializable {

    private val gson by lazy { Gson() }

    @TypeConverter
    fun fromCharactersString(values: String): List<CharacterModel> {
        return gson.fromJson(values, jsonConverter<List<CharacterModel>>())
    }

    @TypeConverter
    fun toCharactersList(list: List<CharacterModel>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromEpisodeToString(values: String): List<Episode> {
        return gson.fromJson(values, jsonConverter<List<Episode>>())
    }

    @TypeConverter
    fun fromEpisodeList(episodes: List<Episode>): String {
        return gson.toJson(episodes)
    }

    @TypeConverter
    fun fromOriginToString(values: String): Origin {
        return gson.fromJson(values, jsonConverter<Origin>())
    }

    @TypeConverter
    fun fromOriginList(origin: Origin): String {
        return gson.toJson(origin)
    }

    @TypeConverter
    fun fromLocationToString(values: String): Location {
        return gson.fromJson(values, jsonConverter<Location>())
    }

    @TypeConverter
    fun fromLocationList(location: Location): String {
        return gson.toJson(location)
    }
}
