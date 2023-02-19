package com.syafii.testbankmandiri.data.repository
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import androidx.paging.PagingData
import com.syafii.testbankmandiri.domain.model.DetailMovieModel
import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.domain.model.MovieModel
import com.syafii.testbankmandiri.domain.model.ReviewModel
import com.syafii.testbankmandiri.domain.model.VideoModel
import com.syafii.testbankmandiri.utils.StateResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getListGenre(): StateResponse<GenreModel>
    suspend fun getMovieByGenrePaging(genreIds: String): Flow<PagingData<MovieModel>>
    suspend fun getDetailMovie(movieId: Int): StateResponse<DetailMovieModel>
    suspend fun getListMovieVideo(movieId: Int): StateResponse<List<VideoModel.VideoItemModel>>
    suspend fun getListMovieReviewsPaging(
        movieId: Int,
        isNextPage: Boolean
    ): Flow<PagingData<ReviewModel>>


}