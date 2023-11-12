package com.alanturing.cpifp.pokemonfragmentsarchitecture.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.cpifp.pokemonfragmentsarchitecture.data.model.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PokemonListViewModel(): ViewModel() {
    private val repository = PokemonRepository.getInstance()
    private val _pokemonListUiState:MutableStateFlow<PokemonListUiState> = MutableStateFlow(PokemonListUiState.Loading)
    val pokemonListUiState: StateFlow<PokemonListUiState>
        get() = _pokemonListUiState.asStateFlow()
    private val pokemonList = repository.pokemonList

    fun fetch() {
        viewModelScope.launch {
            repository.fetch()
            pokemonList.collect {
                pokemonList ->
                    Log.d("DAVID", "esta recibiendo?")
                    val pokemonListUiState = pokemonList.map {
                        p -> Log.d("DAVID","Pokemon: ${p.name}")
                        PokemonListItemUiState(
                            p.id,
                            p.name,
                            p.description
                        )

                    }
                val pokemonUiState = PokemonListUiState.Success(pokemonListUiState)
                _pokemonListUiState.value = pokemonUiState
            }

        }

    }
}