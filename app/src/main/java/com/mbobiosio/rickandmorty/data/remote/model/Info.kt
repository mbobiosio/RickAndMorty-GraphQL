package com.mbobiosio.rickandmorty.data.remote.model

data class Info(
    val count: Int,
    val pages: Int,
    val next: Int?,
    val prev: Int?
) {
    constructor() : this(0, 0, 0, 0)
}
