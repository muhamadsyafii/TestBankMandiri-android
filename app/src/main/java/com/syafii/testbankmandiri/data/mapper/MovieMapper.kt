/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.data.mapper

import com.syafii.testbankmandiri.data.payload.response.MovieResponse
import com.syafii.testbankmandiri.domain.model.MovieModel
import com.syafii.testbankmandiri.utils.orNull


fun MovieResponse.toMovie() : MovieModel {
    return MovieModel(
        adult = adult.orNull(),
        backdropPath = backdropPath.orNull(),
        genreIds = genreIds ?: emptyList(),
        id = id.orNull(),
        originalLanguage = originalLanguage.orNull(),
        originalTitle = originalTitle.orNull(),
        overview = overview.orNull(),
        popularity = popularity.orNull(),
        posterPath = posterPath.orNull(),
        releaseDate = releaseDate.orNull(),
        title = title.orNull(),
        video = video.orNull(),
        voteAverage = voteAverage.orNull(),
        voteCount = voteCount.orNull(),
    )
}
