package com.mbobiosio.rickandmorty.presentation.characterlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mbobiosio.rickandmorty.R
import com.mbobiosio.rickandmorty.databinding.FragmentCharacterBinding
import com.mbobiosio.rickandmorty.domain.model.Character
import com.mbobiosio.rickandmorty.interfaces.CharacterClickListener
import com.mbobiosio.rickandmorty.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character), CharacterClickListener {
    private lateinit var characterListBinding: FragmentCharacterBinding
    private val characterViewModel: CharacterViewModel by viewModels()
    private val characterAdapter = CharacterAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterListBinding = FragmentCharacterBinding.bind(view)

        setupCharacterRecyclerView()
        collectFromViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView

        searchView?.isSubmitButtonEnabled = true

        searchView?.queryHint = "search..."
        searchView?.setQuery(characterViewModel.searchQuery.value, true)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                performSearchEvent(query)
                return false
            }
        })
    }

    private fun performSearchEvent(characterName: String) {
        characterViewModel.onEvent(CharacterEvent.GetAllCharactersByName(characterName))
    }

    private fun setupCharacterRecyclerView() {
        characterAdapter.characterClickListener = this@CharacterFragment
        characterListBinding.apply {
            characterRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = characterAdapter
            }
        }
    }

    private fun collectFromViewModel() {
        lifecycleScope.launch {
            characterViewModel.charactersFlow
                .collectLatest {
                    characterAdapter.submitData(it)
                }
        }
    }

    override fun onCharacterClicked(character: Character?) {
        character?.let {
            findNavController().safeNavigate(
                CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                    it
                )
            )
        }
    }
}
