/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.syafii.testbankmandiri.R
import com.syafii.testbankmandiri.databinding.ActivityDetailMovieBinding
import com.syafii.testbankmandiri.domain.model.DetailMovieModel
import com.syafii.testbankmandiri.domain.model.MovieModel
import com.syafii.testbankmandiri.domain.model.VideoModel
import com.syafii.testbankmandiri.ui.detail.adapter.DetailReviewAdapter
import com.syafii.testbankmandiri.ui.detail.adapter.VideoAdapter
import com.syafii.testbankmandiri.ui.review.ReviewActivity
import com.syafii.testbankmandiri.utils.LoadStateFooterAdapter
import com.syafii.testbankmandiri.utils.StateUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailMovieBinding
    private val viewModel : DetailMovieViewModel by viewModels()
    private var dataExtra: Int? = null
    private val adapterReview by lazy { DetailReviewAdapter() }
    private val adapterVideo by lazy { VideoAdapter() }
    private lateinit var youtubePlayer: YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        subscribeToLiveData()
        initView()
    }

    private fun getData() {
        val intentExtra = intent.getIntExtra(DATA_EXTRA_MOVIE_ID, 0)
        dataExtra = intentExtra

        dataExtra?.let {
            viewModel.getDetailMovie(movieId = it)
            viewModel.getListMovieVideo(movieId = it)
            viewModel.getListMovieReview(movieId = it, isNextPage = false)
        }

    }

    private fun subscribeToLiveData() {
        detailMovieObserver()
        videoMovieObserver()
        reviewMovieObserver()
    }

    private fun initView() {
        binding.run {
            sectionVideos.rvVideo.adapter = adapterVideo
            sectionReview.rvReview.adapter =
                adapterReview.withLoadStateFooter(footer = LoadStateFooterAdapter {
                    adapterReview.refresh()
                })
            adapterReview.addLoadStateListener {
                sectionReview.tvAllReview.isVisible =
                    it.prepend.endOfPaginationReached && adapterReview.itemCount > 0
                sectionReview.tvEmpty.isVisible =
                    it.prepend.endOfPaginationReached && adapterReview.itemCount == 0
            }
            lifecycle.addObserver(sectionVideos.youtubePlayer)
        }

    }


    private fun reviewMovieObserver() {
        viewModel.reviewPaging.observe(this) { pagingReview ->
            adapterReview.submitData(lifecycle,pagingReview)
        }
    }

    private fun videoMovieObserver() {
        viewModel.movieVideosResult.observe(this) {
            when (it) {
                is StateUi.Loading -> {
                    showLoadingVideo(true)
                }
                is StateUi.Failed -> {
                    showLoadingVideo(false)
                    showIsEmptyVideo(true)
                }
                is StateUi.Success -> {
                    showLoadingVideo(false)
                    if (it.data.isEmpty()) {
                        showIsEmptyVideo(true)
                    } else {
                        showIsEmptyVideo(false)
                        setDataVideos(it.data)
                    }
                }
            }
        }
    }

    private fun detailMovieObserver() {
        viewModel.detailMovieResult.observe(this) { data ->
            when (data) {
                is StateUi.Loading -> {
                    showLoadingDetailMovie(true)
                }
                is StateUi.Failed -> {
                    showLoadingDetailMovie(false)
                }
                is StateUi.Success -> {
                    showLoadingDetailMovie(false)
                    setDataMovie(data.data)
                }
            }
        }
    }

    private fun showIsEmptyVideo(isEmpty: Boolean) {
        binding.sectionVideos.rvVideo.isVisible = !isEmpty
        binding.sectionVideos.tvEmpty.isVisible = isEmpty

    }

    private fun showLoadingVideo(isLoading: Boolean) {
        binding.sectionVideos.progressBar.isVisible = isLoading
    }

    private fun setDataVideos(data: List<VideoModel.VideoItemModel>) {
        binding.sectionVideos.youtubePlayer.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youtubePlayer = youTubePlayer
                if (data.isNotEmpty()) {
                    playVideo(data.first().key)
                }
            }
        })

        adapterVideo.setData(data)
        adapterVideo.onItemClick = { item ->
            playVideo(item.key)
        }
    }

    private fun playVideo(key: String) {
        if (this::youtubePlayer.isInitialized) {
            youtubePlayer.loadVideo(key, 0f)
        }
    }

    private fun setDataMovie(data: DetailMovieModel) {
        val releaseYear = data.releaseDate.split("-").first()
        val genreString = data.genres.joinToString(", ") { it.name }
        val dateString = data.releaseDate.replace("-", "/")
        val hourTime = data.runtime / 60
        val minuteTime = data.runtime - (60 * hourTime)
        val longTime = "${hourTime}h${minuteTime}m"

        binding.sectionHeader.run {
            ivBack.setOnClickListener { finish() }
            Glide.with(this@DetailMovieActivity).load(data.posterPathClean).into(ivPoster)
            Glide.with(this@DetailMovieActivity).load(data.backdropPathClean).into(ivBackdrop)

            tvName.text = data.title
            tvTagline.text = data.tagline
            tvYear.text = getString(R.string.label_show_year, releaseYear)
            tvDateGenreHour.text =
                getString(R.string.label_show_date_genre_longtime, dateString, genreString, longTime)
            tvOverview.text = data.overview
        }

        binding.sectionReview.tvAllReview.setOnClickListener {
            val movie = MovieModel(id = data.id, title = data.title)
            val movieString = Gson().toJson(movie)
            val intent = Intent(this@DetailMovieActivity, ReviewActivity::class.java)
            intent.putExtra(ReviewActivity.DATA_EXTRA_MOVIE, movieString)
            startActivity(intent)
        }
    }


    private fun showLoadingDetailMovie(isLoading: Boolean) {
        binding.run {
            sectionHeader.progressGroup.isVisible = isLoading
        }
    }

    companion object {
        const val DATA_EXTRA_MOVIE_ID = "DATA_EXTRA_MOVIE_ID"
    }

}