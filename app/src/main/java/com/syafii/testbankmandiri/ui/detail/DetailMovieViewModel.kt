/*
 * Created by Muhamad Syafii
 * , 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.syafii.testbankmandiri.domain.model.DetailMovieModel
import com.syafii.testbankmandiri.domain.model.ReviewModel
import com.syafii.testbankmandiri.domain.model.VideoModel
import com.syafii.testbankmandiri.domain.usecase.MovieUseCase
import com.syafii.testbankmandiri.utils.StateResponse
import com.syafii.testbankmandiri.utils.StateUi
import com.syafii.testbankmandiri.utils.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val useCase : MovieUseCase) : ViewModel() {

    private val _detailMovie = MutableLiveData<StateUi<DetailMovieModel>>()
    val detailMovieResult: LiveData<StateUi<DetailMovieModel>> = _detailMovie
    private val _movieVideos = MutableLiveData<StateUi<List<VideoModel.VideoItemModel>>>()
    val movieVideosResult: LiveData<StateUi<List<VideoModel.VideoItemModel>>> = _movieVideos
    private val _reviewPaging = MutableLiveData<PagingData<ReviewModel>>()
    val reviewPaging: LiveData<PagingData<ReviewModel>> = _reviewPaging


    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            _detailMovie.postValue(StateUi.Loading)

            val result = useCase.getDetailMovie(movieId = movieId)

            if (result.succeeded) {
                result as StateResponse.Success
                val data = result.data
                _detailMovie.postValue(StateUi.Success("Success", data = data))
            } else {
                result as StateResponse.Failed
                _detailMovie.postValue(StateUi.Failed(result.message))
            }
        }
    }


    fun getListMovieReview(movieId: Int, isNextPage: Boolean = true) {
        viewModelScope.launch {
            val result =
                useCase.getListMovieReviewsPaging(
                    movieId = movieId,
                    isNextPage = isNextPage
                )
                    .cachedIn(viewModelScope)
            result.collect { data ->
                _reviewPaging.postValue(data)
            }
        }
    }

    fun getListMovieVideo(movieId: Int) {
        viewModelScope.launch {
            _movieVideos.postValue(StateUi.Loading)

            val result = useCase.getListMovieVideo(movieId = movieId)

            if (result.succeeded) {
                result as StateResponse.Success
                val data = result.data
                _movieVideos.postValue(StateUi.Success("Success", data = data))
            } else {
                result as StateResponse.Failed
                _movieVideos.postValue(StateUi.Failed(result.message))
            }
        }
    }

}