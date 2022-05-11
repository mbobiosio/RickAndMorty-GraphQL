package com.mbobiosio.rickandmorty.presentation.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mbobiosio.rickandmorty.databinding.ItemCharacterBinding
import com.mbobiosio.rickandmorty.domain.model.Character
import com.mbobiosio.rickandmorty.interfaces.CharacterClickListener

class CharacterAdapter :
    PagingDataAdapter<Character, CharacterAdapter.CharacterListViewHolder>(CharacterDiffUtil()) {
    var characterClickListener: CharacterClickListener? = null

    class CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class CharacterListViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            characterClickListener
            itemView.setOnClickListener {
                characterClickListener?.onCharacterClicked(
                    getItem(absoluteAdapterPosition)
                )
            }
        }

        fun bind(characterItem: Character) {
            binding.apply {
                character = characterItem
                executePendingBindings()
            }
        }
    }
}
