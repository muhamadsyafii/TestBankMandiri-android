package com.syafii.testbankmandiri.data.remote
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import com.syafii.testbankmandiri.data.payload.response.DetailMovieResponse
import com.syafii.testbankmandiri.data.payload.response.GenreResponse
import com.syafii.testbankmandiri.data.payload.response.MovieResponse
import com.syafii.testbankmandiri.data.payload.response.PagingResponse
import com.syafii.testbankmandiri.data.payload.response.ReviewResponse
import com.syafii.testbankmandiri.data.payload.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getListGenre(): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("with_genres") genreIds: String,
        @Query("page") page: Int
    ): Response<PagingResponse<List<MovieResponse>>>

    @GET("movie/{movieId}")
    suspend fun getDetailMovie(
        @Path("movieId") movieId: Int,
    ): Response<DetailMovieResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getListMovieVideo(
        @Path("movieId") movieId: Int,
    ): Response<VideoResponse>

    @GET("movie/{movieId}/reviews")
    suspend fun getListMovieReview(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int,
    ): Response<PagingResponse<List<ReviewResponse>>>

}