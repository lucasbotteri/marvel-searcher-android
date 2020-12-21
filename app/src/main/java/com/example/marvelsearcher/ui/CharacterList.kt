package com.example.marvelsearcher.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelsearcher.MainActivity
import com.example.marvelsearcher.R
import com.example.marvelsearcher.database.entity.CharacterEntity
import com.example.marvelsearcher.databinding.CharacterListFragmentBinding
import com.example.marvelsearcher.ui.adapter.CharacterAdapter
import com.example.marvelsearcher.util.LoadingState
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

        postponeEnterTransition()
        binding.root.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setHasOptionsMenu(true);
    }

    private fun onCharacterClicked(
        characterEntity: CharacterEntity,
        characterImage: ImageView,
        characterName: TextView
    ){
        val extras = FragmentNavigatorExtras(
            characterImage to characterEntity.id.toString(),
            characterName to characterEntity.name
        )
        val action = CharacterListDirections.actionCharacterListToCharacterDetails(
            characterEntity.id,
            characterEntity.imageUrl,
            characterEntity.name
        )
        findNavController().navigate(action, extras)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.characters.observe(viewLifecycleOwner, characterAdapter::submitList)
        viewModel.charactersListLoadingState.observe(viewLifecycleOwner, Observer { loadingState ->
            when (loadingState) {
                LoadingState.LOADING -> {
                    binding.characterRecyclerView.visibility = View.GONE
                    binding.loadingProgressBar.visibility = View.VISIBLE
                    binding.noResultText.visibility = View.GONE
                }
                LoadingState.LOADED -> {
                    binding.characterRecyclerView.visibility = View.VISIBLE
                    binding.loadingProgressBar.visibility = View.GONE
                    binding.noResultText.visibility = View.GONE
                }
                LoadingState.NO_CONTENT -> {
                    binding.characterRecyclerView.visibility = View.GONE
                    binding.loadingProgressBar.visibility = View.GONE
                    binding.noResultText.visibility = View.VISIBLE
                }
                else -> {

                }
            }
        })
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.options_menu, menu)
        val menuItem = menu.findItem(R.id.search)
        val searchView = SearchView((activity as MainActivity).supportActionBar?.themedContext )
        searchView.isIconified = false
        searchView.isIconifiedByDefault = false
        menuItem.actionView = searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchCharactersByName(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })

    }

}
