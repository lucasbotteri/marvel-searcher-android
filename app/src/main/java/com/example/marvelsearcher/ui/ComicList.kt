package com.example.marvelsearcher.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.marvelsearcher.R
import com.example.marvelsearcher.viewmodel.ComicListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ComicList : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ComicListViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comic_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
       viewModel.infoText.observe(viewLifecycleOwner, {text -> Log.d("aaaa", text)})
    }

}
