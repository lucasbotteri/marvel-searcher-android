package com.example.marvelsearcher.ui

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.marvelsearcher.databinding.CharacterDetailsFragmentBinding
import com.example.marvelsearcher.viewmodel.CharacterDetailsViewModel
import com.example.marvelsearcher.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CharacterDetails : DaggerFragment() {

    private val args: CharacterDetailsArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CharacterDetailsViewModel by viewModels { viewModelFactory }

    lateinit var binding: CharacterDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterDetailsFragmentBinding.inflate(inflater)
        viewModel.character.observe(viewLifecycleOwner,{
            binding.character = it
            binding.imageUrl = it.imageUrl
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        // TODO is the best way of doing this?
        postponeEnterTransition()
        viewModel.getCharacter(args.characterId)
        binding.characterDetailImage.transitionName = args.characterId.toString()
        binding.imageUrl = args.imageUrl
        binding.characterDetailName.transitionName = args.characterName
        binding.characterDetailName.text = args.characterName
        startEnterTransitionAfterLoadingImage()
    }

    private fun startEnterTransitionAfterLoadingImage() {
        Glide.with(this)
                .load(args.imageUrl)
                .dontAnimate() // 1
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: com.bumptech.glide.request.target.Target<Drawable>,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(binding.characterDetailImage)
    }

}
