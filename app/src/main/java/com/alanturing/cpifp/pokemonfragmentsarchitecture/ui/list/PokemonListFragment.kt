package com.alanturing.cpifp.pokemonfragmentsarchitecture.ui.list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alanturing.cpifp.pokemonfragmentsarchitecture.databinding.ActivityMainBinding
import com.alanturing.cpifp.pokemonfragmentsarchitecture.databinding.FragmentPokemonListBinding
import com.alanturing.cpifp.pokemonfragmentsarchitecture.ui.common.BaseFragment
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class PokemonListFragment : BaseFragment() {
    private val viewModel:PokemonListViewModel by activityViewModels()
    private lateinit var binding: FragmentPokemonListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = PokemonListAdapter()
        binding.pokemonList.adapter = adapter
        viewModel.fetch()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonListUiState.collect {
                    Log.d("DAVID",it.toString())
                    when (it) {
                        is PokemonListUiState.Loading -> {
                            Log.d("DAVID","loading...")
                            binding.progressBar.visibility = CircularProgressIndicator.VISIBLE
                        }
                        is PokemonListUiState.Success -> {
                            Log.d("DAVID","Success!!")
                            binding.progressBar.visibility = CircularProgressIndicator.GONE
                            adapter.submitList(it.pokemonItems)
                        }
                    }
                }
            }
        }

    }
}