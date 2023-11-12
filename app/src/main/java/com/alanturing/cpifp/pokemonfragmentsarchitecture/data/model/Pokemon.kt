package com.alanturing.cpifp.pokemonfragmentsarchitecture.data.model

data class Pokemon(
    val id:Int,
    val name:String,
    val description:String? = null,
    val primaryType: PokemonType? = null,
    val secondaryType: PokemonType? = null
)
