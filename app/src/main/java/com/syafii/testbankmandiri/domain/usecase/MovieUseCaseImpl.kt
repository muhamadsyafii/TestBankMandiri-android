package com.syafii.testbankmandiri.domain.usecase
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import com.syafii.testbankmandiri.data.repository.MovieRepository
import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.utils.StateResponse
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(private val repository: MovieRepository) : MovieUseCase {
    override suspend fun getListGenre(): StateResponse<GenreModel> {
        return repository.getListGenre()
    }
}