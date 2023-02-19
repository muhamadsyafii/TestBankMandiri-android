/*
 * Created by Muhamad Syafii
 * , 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.syafii.testbankmandiri.domain.model.ReviewModel
import com.syafii.testbankmandiri.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ReviewViewModel @Inject constructor(private val useCase : MovieUseCase) : ViewModel() {
    private val _reviewPaging = MutableLiveData<PagingData<ReviewModel>>()
    val reviewPaging: LiveData<PagingData<ReviewModel>> = _reviewPaging

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
}