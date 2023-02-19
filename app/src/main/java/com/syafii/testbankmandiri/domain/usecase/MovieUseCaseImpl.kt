package com.syafii.testbankmandiri.domain.usecase
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import androidx.paging.PagingData
import com.syafii.testbankmandiri.data.repository.MovieRepository
import com.syafii.testbankmandiri.domain.model.DetailMovieModel
import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.domain.model.MovieModel
import com.syafii.testbankmandiri.domain.model.ReviewModel
import com.syafii.testbankmandiri.domain.model.VideoModel
import com.syafii.testbankmandiri.utils.StateResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MovieUseCaseImpl @Inject constructor(private val repository: MovieRepository) : MovieUseCase {
    override suspend fun getListGenre(): StateResponse<GenreModel> {
        return repository.getListGenre()
    }

    override suspend fun getMovieByGenrePaging(genreIds: String): Flow<PagingData<MovieModel>> {
        return repository.getMovieByGenrePaging(genreIds = genreIds)
    }

    override suspend fun getDetailMovie(movieId: Int): StateResponse<DetailMovieModel> {
        return repository.getDetailMovie(movieId = movieId)
    }

    override suspend fun getListMovieVideo(movieId: Int): StateResponse<List<VideoModel.VideoItemModel>> {
        return repository.getListMovieVideo(movieId = movieId)
    }

    override suspend fun getListMovieReviewsPaging(
        movieId: Int,
        isNextPage: Boolean,
    ): Flow<PagingData<ReviewModel>> {
        return repository.getListMovieReviewsPaging(movieId, isNextPage = isNextPage)
    }
}