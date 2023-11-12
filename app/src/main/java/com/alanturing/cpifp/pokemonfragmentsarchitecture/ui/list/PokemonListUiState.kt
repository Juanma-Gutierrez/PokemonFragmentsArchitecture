package com.alanturing.cpifp.pokemonfragmentsarchitecture.ui.list


sealed class PokemonListUiState {
    data object Loading: PokemonListUiState()
    data class Success(val pokemonItems:List<PokemonListItemUiState>): PokemonListUiState()
}

