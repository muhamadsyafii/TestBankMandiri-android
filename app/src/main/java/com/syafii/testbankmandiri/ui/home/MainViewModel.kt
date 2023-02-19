/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.domain.usecase.MovieUseCase
import com.syafii.testbankmandiri.utils.StateResponse
import com.syafii.testbankmandiri.utils.StateUi
import com.syafii.testbankmandiri.utils.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    private val _genre = MutableLiveData<StateUi<GenreModel>>()
    val genreResult: LiveData<StateUi<GenreModel>> = _genre

    fun getListGenre() {
        viewModelScope.launch {
            _genre.postValue(StateUi.Loading)

            val result = useCase.getListGenre()

            if (result.succeeded) {
                result as StateResponse.Success
                val data = result.data
                _genre.postValue(StateUi.Success("Success", data = data))
            } else {
                result as StateResponse.Failed
                _genre.postValue(StateUi.Failed(result.message))
            }
        }
    }

}