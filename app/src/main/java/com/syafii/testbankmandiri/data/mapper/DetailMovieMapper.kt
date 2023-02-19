/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.data.mapper

import com.syafii.testbankmandiri.data.payload.response.DetailMovieResponse
import com.syafii.testbankmandiri.domain.model.DetailMovieModel
import com.syafii.testbankmandiri.utils.orNull
import com.syafii.testbankmandiri.utils.orNullListNot

fun DetailMovieResponse.toDetailMovie() : DetailMovieModel {
    return DetailMovieModel(
        originalLanguage = originalLanguage.orNull(),
        imdbId = imdbId.orNull(),
        title = title.orNull(),
        backdropPath = backdropPath.orNull(),
        revenue = revenue.orNull(),
        popularity = popularity.orNull(),
        id = id.orNull(),
        voteCount = voteCount.orNull(),
        budget = budget.orNull(),
        originalTitle = originalTitle.orNull(),
        runtime = runtime.orNull(),
        posterPath = posterPath.orNull(),
        releaseDate = releaseDate.orNull(),
        voteAverage = voteAverage.orNull(),
        tagline = tagline.orNull(),
        adult = adult.orNull(),
        status = status.orNull(),
        genres = genres.orNullListNot {it.toItemGenre()},
        overview = overview.orNull(),
    )
}

