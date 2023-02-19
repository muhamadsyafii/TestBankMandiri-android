package com.syafii.testbankmandiri.data.repository
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.syafii.testbankmandiri.data.mapper.toDetailMovie
import com.syafii.testbankmandiri.data.mapper.toGenre
import com.syafii.testbankmandiri.data.mapper.toVideo
import com.syafii.testbankmandiri.data.pagingsource.MoviePagingSource
import com.syafii.testbankmandiri.data.pagingsource.ReviewPagingSource
import com.syafii.testbankmandiri.data.remote.ApiService
import com.syafii.testbankmandiri.domain.model.DetailMovieModel
import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.domain.model.MovieModel
import com.syafii.testbankmandiri.domain.model.ReviewModel
import com.syafii.testbankmandiri.domain.model.VideoModel
import com.syafii.testbankmandiri.utils.ErrorUtil
import com.syafii.testbankmandiri.utils.StateResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl @Inject constructor(private val service: ApiService) : MovieRepository {
    override suspend fun getListGenre(): StateResponse<GenreModel> {
        return try {
            val response = service.getListGenre()
            if (response.isSuccessful) {
                val data = response.body()?.toGenre() ?: GenreModel()
                StateResponse.Success(data = data)
            } else {
                val message = ErrorUtil.getErrorMessage(response.errorBody()?.string() ?: "")
                StateResponse.Failed(message)
            }

        } catch (e: Exception) {
            StateResponse.Failed(e.localizedMessage ?: "Error Exception")
        }
    }

    override suspend fun getMovieByGenrePaging(genreIds: String): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(pageSize = 5, prefetchDistance = 1),
            pagingSourceFactory = { MoviePagingSource(service, genreIds) }
        ).flow

    }

    override suspend fun getDetailMovie(movieId: Int): StateResponse<DetailMovieModel> {
        return try {
            val response = service.getDetailMovie(movieId)
            if (response.isSuccessful) {
                val data = response.body()?.toDetailMovie() ?: DetailMovieModel()
                StateResponse.Success(data = data)
            } else {
                val message = ErrorUtil.getErrorMessage(response.errorBody()?.string() ?: "")
                StateResponse.Failed(message)
            }

        } catch (e: Exception) {
            StateResponse.Failed(e.localizedMessage ?: "Error Exception")
        }

    }

    override suspend fun getListMovieVideo(movieId: Int): StateResponse<List<VideoModel.VideoItemModel>> {
        return try {
            val response = service.getListMovieVideo(movieId)
            if (response.isSuccessful) {
                val data = response.body()?.toVideo()?.results ?: emptyList()
                StateResponse.Success(data = data)
            } else {
                val message = ErrorUtil.getErrorMessage(response.errorBody()?.string() ?: "")
                StateResponse.Failed(message)
            }

        } catch (e: Exception) {
            StateResponse.Failed(e.localizedMessage ?: "Error Exception")
        }

    }

    override suspend fun getListMovieReviewsPaging(
        movieId: Int,
        isNextPage: Boolean,
    ): Flow<PagingData<ReviewModel>> {
        return Pager(
            config = PagingConfig(pageSize = 5, prefetchDistance = 1),
            pagingSourceFactory = { ReviewPagingSource(service, movieId, isNextPage) }
        ).flow

    }
}