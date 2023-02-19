/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.gson.Gson
import com.syafii.testbankmandiri.R
import com.syafii.testbankmandiri.databinding.ActivityMovieBinding
import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.domain.model.MovieModel
import com.syafii.testbankmandiri.ui.detail.DetailMovieActivity
import com.syafii.testbankmandiri.ui.movie.adapter.MovieAdapter
import com.syafii.testbankmandiri.utils.LoadStateFooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var dataExtra: GenreModel.GenreItemModel

    private val adapter by lazy {
        MovieAdapter().apply {
            onItemClick = { data ->
                handleOnClick(data)
            }
        }
    }

    private fun handleOnClick(data: MovieModel) {
        val intent = Intent(this@MovieActivity, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.DATA_EXTRA_MOVIE_ID, data.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        subscribeToLiveData()
        initView()
    }

    private fun subscribeToLiveData() {
        viewModel.moviePaging.observe(this){ data ->
            adapter.submitData(lifecycle, data)
        }
    }

    private fun initView() {
        binding.run {
            ivBack.setOnClickListener { finish() }

            rvMovie.adapter = adapter.withLoadStateFooter(footer = LoadStateFooterAdapter {
                adapter.retry()
            })
            tvTitleMovie.text = getString(R.string.label_movie_name, dataExtra.name)

            layoutEmpty.btnReload.setOnClickListener {
                adapter.refresh()
            }
            adapter.addLoadStateListener {
                if (it.append.endOfPaginationReached) {
                    if (adapter.itemCount == 0) {
                        layoutEmpty.root.isVisible = true
                    }
                }
                binding.progressBar.isVisible = it.source.refresh is LoadState.Loading
                layoutEmpty.root.isVisible = it.refresh is LoadState.Error
            }
        }

    }

    private fun getData() {
        val intentExtra = intent.getStringExtra(DATA_EXTRA_GENRE)
        if (!intentExtra.isNullOrEmpty()) {
            dataExtra = Gson().fromJson(intentExtra, GenreModel.GenreItemModel::class.java)
            viewModel.getMovieByGenrePaging(dataExtra.id)
        }
    }


    companion object {
        const val DATA_EXTRA_GENRE = "DATA_EXTRA_ID"
    }

}