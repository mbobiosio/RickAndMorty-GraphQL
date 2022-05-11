package com.mbobiosio.rickandmorty.data.repository

import com.mbobiosio.rickandmorty.GetCharactersQuery

/*
* Created by Mbuodile Obiosio on May 11, 2022.
* Twitter: @cazewonder
* Nigeria
*/
interface RemoteDataSource {
    suspend fun getCharacters(page: Int): GetCharactersQuery.Characters
}
