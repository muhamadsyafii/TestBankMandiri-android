/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syafii.testbankmandiri.R
import com.syafii.testbankmandiri.databinding.ItemReviewDetailBinding
import com.syafii.testbankmandiri.domain.model.ReviewModel
import com.syafii.testbankmandiri.utils.FormatterUtil

class ReviewAdapter : PagingDataAdapter<ReviewModel, ReviewAdapter.MyViewHolder>(ReviewDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemReviewDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }

    inner class MyViewHolder(private val binding : ItemReviewDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: ReviewModel) {
            binding.run {
                val writtenAt = formatterDate(item.createdAt)
                Glide.with(binding.root.context)
                    .load(item.authorDetails.avatarPathClean)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(ivImage)
                tvName.text = root.context.getString(R.string.label_written_by, item.author)
                tvCreatedAt.text = root.context.getString(R.string.label_written_at, writtenAt)
                tvDescription.text = item.content
                tvRating.text = item.authorDetails.rating.toString()
            }
        }

    }

    private fun formatterDate(createdAt: String): String {
        val toDate = FormatterUtil.stringToDate(createdAt)
        return FormatterUtil.dateToString(toDate, formatTo = "MMMM dd, yyyy")
    }



    class ReviewDiffCallBack : DiffUtil.ItemCallback<ReviewModel>() {
        override fun areItemsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
           return oldItem == newItem
        }

    }
}