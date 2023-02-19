/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.domain.model

import com.syafii.testbankmandiri.BuildConfig

data class DetailMovieModel(
    val originalLanguage: String = "",
    val imdbId: String = "",
    val title: String = "",
    val backdropPath: String = "",
    val revenue: Int = 0,
    val popularity: Double = 0.0,
    val id: Int = 0,
    val voteCount: Int = 0,
    val budget: Int = 0,
    val originalTitle: String = "",
    val runtime: Int = 0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
    val tagline: String = "",
    val adult: Boolean = false,
    val status: String = "",
    val genres: List<GenreModel.GenreItemModel> = emptyList(),
    val overview: String = "",
){
    val posterPathClean = BuildConfig.BASE_URL_IMAGE + posterPath
    val backdropPathClean = BuildConfig.BASE_URL_IMAGE + backdropPath
}