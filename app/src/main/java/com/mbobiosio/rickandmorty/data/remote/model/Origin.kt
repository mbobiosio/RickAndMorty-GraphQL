package com.mbobiosio.rickandmorty.data.remote.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "origins")
@Parcelize
data class Origin(
    @PrimaryKey
    val id: String,
    val name: String
) : Parcelable {
    constructor() : this("", "")
}
