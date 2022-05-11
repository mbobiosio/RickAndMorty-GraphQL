package com.mbobiosio.rickandmorty.interfaces

import com.mbobiosio.rickandmorty.domain.model.Character

interface CharacterClickListener {
    fun onCharacterClicked(character: Character?)
}
