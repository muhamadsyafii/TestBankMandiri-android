/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.data.payload.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("budget")
    val budget: Int?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("genres")
    val genres: List<GenreResponse.GenreItemResponse>?,
    @SerializedName("overview")
    val overview: String?,
)