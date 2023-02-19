/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.syafii.testbankmandiri.databinding.ViewFooterLoadingBinding

class LoadStateFooterAdapter(private val onclick: () -> Unit) :
    LoadStateAdapter<LoadStateFooterAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MyViewHolder {
        return MyViewHolder(ViewFooterLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, loadState: LoadState) {
       holder.bindData(loadState)
    }

    inner class MyViewHolder(private val binding: ViewFooterLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(loadState: LoadState) {
            binding.run {
                tvNote.isVisible = loadState is LoadState.Error || loadState is LoadState.NotLoading
                btnRetry.isVisible = loadState is LoadState.Error
                progressCircular.isVisible = loadState is LoadState.Loading

                if (loadState is LoadState.Error) {
                    tvNote.text = loadState.error.message ?: "Failed load data"
                    btnRetry.setOnClickListener {
                        onclick.invoke()
                    }
                }
            }

        }

    }

}