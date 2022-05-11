package com.mbobiosio.rickandmorty.data.locale.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey val characterId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
