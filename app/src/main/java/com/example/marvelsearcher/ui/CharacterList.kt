package com.example.marvelsearcher.ui

import android.app.SearchManager
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

    lateinit var queryTextListener: SearchView.OnQueryTextListener

    lateinit var characterAdapter: CharacterAdapter

    private var searchView: SearchView? = null


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

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.options_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchManager = activity?.getSystemService(activity?.componentName.toString()) as SearchManager

        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName));

            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    Log.i("onQueryTextChange", newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.i("onQueryTextSubmit", query)
                    return true
                }
            }
            searchView!!.setOnQueryTextListener(queryTextListener)
        }
        return super.onCreateOptionsMenu(menu, menuInflater)
    }

}
