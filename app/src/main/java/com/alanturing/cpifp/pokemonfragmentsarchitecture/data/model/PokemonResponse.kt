package com.alanturing.cpifp.pokemonfragmentsarchitecture.data.model

import java.lang.invoke.TypeDescriptor

data class PokemonListResponseItem(
    val name:String,
    val url:String
)
data class PokemonListResponse(
    val count:Int,
    val next:String? = null,
    val previous:String? = null,
    val results: List<PokemonListResponseItem>
)
data class PokemonDetailResponse(
    val id:Int,
    val name:String,
)
