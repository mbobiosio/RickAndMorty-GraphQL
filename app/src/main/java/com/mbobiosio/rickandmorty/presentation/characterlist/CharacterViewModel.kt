package com.mbobiosio.rickandmorty.presentation.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mbobiosio.rickandmorty.domain.model.Character
import com.mbobiosio.rickandmorty.domain.usecase.GetCharactersByName
import com.mbobiosio.rickandmorty.utils.Constants.Companion.WAIT_TIME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersByNameUseCase: GetCharactersByName
) : ViewModel() {
    private val _charactersFlow = MutableSharedFlow<PagingData<Character>>()
    val charactersFlow = _charactersFlow.asSharedFlow()

    private val _searchQuery: MutableStateFlow<String> =
        MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    private var searchJob: Job? = null

    fun onEvent(event: CharacterEvent) {
        when (event) {
            is CharacterEvent.GetAllCharactersByName -> onSearch(event.characterName)
        }
    }

    init {
        getCharactersByName("")
    }

    private fun getCharactersByName(characterName: String) {
        getCharactersByNameUseCase(characterName).onEach {
            _charactersFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    private fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(WAIT_TIME)
            getCharactersByName(query)
        }
    }
}
