/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.syafii.testbankmandiri.domain.model.MovieModel
import com.syafii.testbankmandiri.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieViewModel @Inject constructor(private val useCase: MovieUseCase): ViewModel(){

    private val _moviePaging = MutableLiveData<PagingData<MovieModel>>()
    val moviePaging: LiveData<PagingData<MovieModel>> = _moviePaging

    fun getMovieByGenrePaging(genreIds: String) {
        viewModelScope.launch {
            val result = useCase.getMovieByGenrePaging(genreIds = genreIds).cachedIn(viewModelScope)
            result.collect { data ->
                _moviePaging.postValue(data)
            }
        }
    }

}