/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.google.gson.Gson
import com.syafii.testbankmandiri.R
import com.syafii.testbankmandiri.databinding.ActivityReviewBinding
import com.syafii.testbankmandiri.domain.model.MovieModel
import com.syafii.testbankmandiri.domain.model.ReviewModel
import com.syafii.testbankmandiri.ui.review.adapter.ReviewAdapter
import com.syafii.testbankmandiri.utils.LoadStateFooterAdapter
import com.syafii.testbankmandiri.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding
    private var dataExtra: MovieModel? = null
    private val viewModel: ReviewViewModel by viewModels()
    private val adapter by lazy { ReviewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        subscribeToLiveData()
        initView()
    }

    private fun getData() {
        val intentExtra = intent.getStringExtra(DATA_EXTRA_MOVIE)
        dataExtra = Gson().fromJson(intentExtra, MovieModel::class.java)
        dataExtra?.let {
            viewModel.getListMovieReview(movieId = it.id)
        }
    }

    private fun subscribeToLiveData() {
        viewModel.reviewPaging.observe(this) { pagingReview ->
            adapter.submitData(lifecycle, pagingReview)
        }
    }


    private fun initView() {
        binding.run {
            ivBack.setOnClickListener { finish() }
            tvLabelReview.text = getString(R.string.label_review_movie, dataExtra?.title)
            rvReview.adapter =
                adapter.withLoadStateFooter(LoadStateFooterAdapter { adapter.refresh() })

            layoutEmpty.btnReload.setOnClickListener {
                adapter.refresh()
            }

            adapter.addLoadStateListener {
                if (it.append.endOfPaginationReached) {
                    layoutEmpty.root.isVisible = adapter.itemCount == 0
                }

                binding.progressBar.isVisible = it.source.refresh is LoadState.Loading
                layoutEmpty.root.isVisible = it.refresh is LoadState.Error
                rvReview.isVisible = it.refresh !is LoadState.Error
                if (it.refresh is LoadState.Error) {
                    val message = (it.refresh as LoadState.Error).error.localizedMessage
                    showToast(message ?: "")
                }
            }
        }
    }

    companion object {
        const val DATA_EXTRA_MOVIE = "DATA_EXTRA_MOVIE"
    }

}