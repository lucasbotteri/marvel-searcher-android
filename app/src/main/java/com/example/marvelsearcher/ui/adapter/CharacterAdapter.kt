package com.example.marvelsearcher.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelsearcher.R
import com.example.marvelsearcher.database.entity.CharacterEntity
import com.example.marvelsearcher.databinding.CharacterItemBinding

class CharacterAdapter(val onClick: (CharacterEntity, ImageView, TextView) -> Unit):
    ListAdapter<CharacterEntity, CharacterAdapter.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val withDataBinding:  CharacterItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false)
        return ViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.character =  getItem(position)
        holder.viewDataBinding.characterListener = CharacterListener {
            onClick(it, holder.viewDataBinding.characterItemImage, holder.viewDataBinding.characterItemName)
        }
        // TODO should i do this?
        holder.viewDataBinding.executePendingBindings()
    }


    class ViewHolder(val viewDataBinding: CharacterItemBinding): RecyclerView.ViewHolder(viewDataBinding.root){

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.character_item
        }

    }

    class DiffCallback: DiffUtil.ItemCallback<CharacterEntity>() {
        override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean  = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CharacterEntity,
            newItem: CharacterEntity
        ): Boolean = oldItem == newItem
    }

    class CharacterListener(val block: (CharacterEntity) -> Unit) {
        fun onClick(character: CharacterEntity) = block(character)
    }


}
