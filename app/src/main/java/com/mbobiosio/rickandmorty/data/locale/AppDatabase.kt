package com.mbobiosio.rickandmorty.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mbobiosio.rickandmorty.data.locale.AppDatabase.Companion.DB_VERSION
import com.mbobiosio.rickandmorty.data.locale.dao.CharacterDao
import com.mbobiosio.rickandmorty.data.locale.dao.RemoteKeyDao
import com.mbobiosio.rickandmorty.data.locale.entity.RemoteKeyEntity
import com.mbobiosio.rickandmorty.data.remote.model.CharacterModel

@Database(entities = [CharacterModel::class, RemoteKeyEntity::class], version = DB_VERSION)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val remoteKeyDao: RemoteKeyDao

    companion object {
        const val DB_VERSION = 3
    }
}
