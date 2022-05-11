package com.mbobiosio.rickandmorty.presentation.characterdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mbobiosio.rickandmorty.R
import com.mbobiosio.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.mbobiosio.rickandmorty.domain.model.Character
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var char: Character

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterDetailBinding.bind(view)
        getArgs()
    }

    private fun getArgs() {
        char = args.character

        Timber.d("Character $char")
        bindCharacterDetails()
    }

    private fun bindCharacterDetails() {
        binding.apply {
            char.let {
                character = it
            }
        }
    }
}
