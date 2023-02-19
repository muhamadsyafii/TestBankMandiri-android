/*
 * Created by Muhamad Syafii
 * Sunday , 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.syafii.testbankmandiri.databinding.ItemVideoBinding
import com.syafii.testbankmandiri.domain.model.VideoModel

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>(){

    private val items = arrayListOf<VideoModel.VideoItemModel>()

    fun setData(data: List<VideoModel.VideoItemModel>) {
        val itemCount = items.size
        items.clear()
        notifyItemRangeRemoved(0, itemCount)
        items.addAll(data)
        notifyItemRangeInserted(0, data.size)
        if (items.isNotEmpty()) {
            selectItem(items.first(), 0)
        }
    }

    private var selectedPosition = -1
    var onItemClick: ((VideoModel.VideoItemModel) -> Unit)? = null


    private fun selectItem(item: VideoModel.VideoItemModel, position: Int) {
        val oldPosition = selectedPosition
        selectedPosition = position
        if (oldPosition > -1) {
            notifyItemChanged(oldPosition)
        }
        notifyItemChanged(selectedPosition)
        onItemClick?.invoke(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bindData(item = items[position], position)
    }

    override fun getItemCount() = items.size

    inner class VideoViewHolder(private val binding : ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: VideoModel.VideoItemModel, position: Int) {
            binding.run {
                tvType.text = item.type
                tvTitle.text = item.name

                ivIsSelected.isVisible = selectedPosition == position
                root.setOnClickListener {
                    selectItem(item, position)
                }
            }
        }
    }
}