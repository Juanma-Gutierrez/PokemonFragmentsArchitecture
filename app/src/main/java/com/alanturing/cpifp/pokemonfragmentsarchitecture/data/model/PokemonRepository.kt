package com.alanturing.cpifp.pokemonfragmentsarchitecture.data.model

import android.util.Log
import retrofit2.Retrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface  PokemonApi {
    @GET("pokemon/?limit=40")
    suspend fun getPokemon():PokemonListResponse
    @GET("pokemon/{id}")
    suspend fun getDetails(@Path("id") id:String):PokemonDetailResponse
}
private const val BASE_URL = "https://pokeapi.co/api/v2/"

class PokemonRepository private constructor(private val pokemonApi:PokemonApi) {

    /*private val _pokemonList = MutableSharedFlow<List<Pokemon>>()
    val pokemonList: Flow<List<Pokemon>>
        get() = _pokemonList*/
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(listOf())
    val pokemonList: StateFlow<List<Pokemon>>
        get() = _pokemonList
    companion object {
        private var _INSTANCE: PokemonRepository? = null
        fun  getInstance():PokemonRepository {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val pokemonApi = retrofit.create(PokemonApi::class.java)
                _INSTANCE = _INSTANCE ?: PokemonRepository(pokemonApi)
                return _INSTANCE!!
        }
    }

    suspend fun fetch() {
        Log.d("DAVID","Fetching...")
        try {
            val listResponse = pokemonApi.getPokemon()
            val detailResponse = listResponse.results.map {responseItem ->
                val segments = responseItem.url.trimEnd('/').split("/")
                val details = pokemonApi.getDetails(segments.last().toString())
                Pokemon(id=details.id,
                    name=details.name)
            }
            Log.d("DAVID","Emitiendo: ${detailResponse.size.toString()} items")

            _pokemonList.value = detailResponse
        }
        catch (e:Exception) {
            Log.e("DAVID","Se ha producido una excepción ${e.message}")

        }
    }
}