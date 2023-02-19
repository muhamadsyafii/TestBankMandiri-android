/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.syafii.testbankmandiri.databinding.ItemGenreBinding
import com.syafii.testbankmandiri.domain.model.GenreModel

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(){

    private val items: ArrayList<GenreModel.GenreItemModel> = arrayListOf()
    private var itemsSearch: List<GenreModel.GenreItemModel> = emptyList()

    var onItemClick : ((GenreModel.GenreItemModel) -> Unit)? = null

    fun setData(data: List<GenreModel.GenreItemModel>) {
        items.clear()
        items.addAll(data)
        searchGenre("")
    }

    //search item
    fun searchGenre(text: String) {
        val temp = itemsSearch
        itemsSearch = items.filter { it.name.contains(text, true) }

        val diffCallback = GenreDiffCallback(temp, itemsSearch)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bindData(itemsSearch[position])
    }

    inner class GenreViewHolder(private val binding : ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: GenreModel.GenreItemModel) {
            binding.tvName.text = item.name
            binding.root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }

    }
    override fun getItemCount() = itemsSearch.size

    inner class GenreDiffCallback(
        private val oldItems: List<GenreModel.GenreItemModel>,
        private val newItems: List<GenreModel.GenreItemModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldItems.size

        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition].id == newItems[newItemPosition].id
        }
    }
}