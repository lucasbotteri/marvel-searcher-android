package com.example.marvelsearcher.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.marvelsearcher.R
import com.example.marvelsearcher.databinding.CharacterListFragmentBinding
import com.example.marvelsearcher.viewmodel.CharacterListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CharacterList : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: CharacterListFragmentBinding

    private val viewModel: CharacterListViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListFragmentBinding.inflate(inflater)

        viewModel.characters.observe(viewLifecycleOwner, {ch -> Log.d("First character", if (ch.isNotEmpty()) ch.first().name else "Empty List" )})
        binding.button.setOnClickListener {
            viewModel.searchCharacter()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
