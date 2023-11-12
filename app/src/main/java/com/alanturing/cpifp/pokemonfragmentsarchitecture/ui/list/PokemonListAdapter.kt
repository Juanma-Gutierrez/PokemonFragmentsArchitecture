package com.alanturing.cpifp.pokemonfragmentsarchitecture.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alanturing.cpifp.pokemonfragmentsarchitecture.databinding.ListItemPokemonBinding

class PokemonListAdapter():ListAdapter<PokemonListItemUiState,PokemonListAdapter.PokemListItemViewHolder>(PokemonDiffCallback) {

    inner class PokemListItemViewHolder(private val binding:ListItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon:PokemonListItemUiState){
            binding.nameText.text = pokemon.name
            binding.decriptionText.text = pokemon.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemListItemViewHolder {
        return PokemListItemViewHolder(ListItemPokemonBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object PokemonDiffCallback: DiffUtil.ItemCallback<PokemonListItemUiState>() {
        override fun areItemsTheSame(oldItem: PokemonListItemUiState, newItem: PokemonListItemUiState) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PokemonListItemUiState, newItem: PokemonListItemUiState) = oldItem == newItem

    }
}