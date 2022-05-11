package com.mbobiosio.rickandmorty.data.remote.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "locations")
@Parcelize
data class Location(
    @PrimaryKey
    val id: String,
    val name: String
) : Parcelable {
    constructor() : this("", "")
}
