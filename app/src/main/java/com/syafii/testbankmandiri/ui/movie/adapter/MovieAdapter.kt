/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syafii.testbankmandiri.databinding.ItemMovieBinding
import com.syafii.testbankmandiri.domain.model.MovieModel

class MovieAdapter : PagingDataAdapter<MovieModel, MovieAdapter.MovieHolder>(MovieDiffCallback()) {

    var onItemClick : (MovieModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }

    inner class MovieHolder (private val binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(data : MovieModel) {
            binding.run {
                Glide.with(binding.root.context).load(data.getPosterPath).into(ivImage)
                tvName.text = data.title
                root.setOnClickListener {
                    onItemClick.invoke(data)
                }
            }
        }

    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }
}