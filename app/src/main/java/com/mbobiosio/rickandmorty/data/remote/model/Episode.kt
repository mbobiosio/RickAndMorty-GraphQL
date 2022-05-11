package com.mbobiosio.rickandmorty.data.remote.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/*
* Created by Mbuodile Obiosio on May 11, 2022.
* Twitter: @cazewonder
* Nigeria
*/
@Entity(tableName = "episodes")
@Parcelize
data class Episode(
    @PrimaryKey
    val id: String,
    val name: String
) : Parcelable
