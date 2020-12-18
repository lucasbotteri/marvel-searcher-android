package com.example.marvelsearcher.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelsearcher.R
import com.example.marvelsearcher.database.entity.CharacterEntity
import com.example.marvelsearcher.databinding.CharacterListFragmentBinding
import com.example.marvelsearcher.ui.adapter.CharacterAdapter
import com.example.marvelsearcher.viewmodel.CharacterListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CharacterList : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: CharacterListFragmentBinding

    private val viewModel: CharacterListViewModel by viewModels { viewModelFactory }

    lateinit var characterAdapter: CharacterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListFragmentBinding.inflate(inflater)


        characterAdapter = CharacterAdapter(::onCharacterClicked)
        binding.characterRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.characterRecyclerView.adapter = characterAdapter

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    private fun onCharacterClicked(characterEntity: CharacterEntity, characterImage: ImageView, characterName: TextView){
        val extras = FragmentNavigatorExtras(characterImage to characterEntity.id.toString(), characterName to characterEntity.name)
        val action = CharacterListDirections.actionCharacterListToCharacterDetails(characterEntity.id, characterEntity.imageUrl, characterEntity.name)
        findNavController().navigate(action, extras)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.characters.observe(viewLifecycleOwner, characterAdapter::submitList)
    }

}
