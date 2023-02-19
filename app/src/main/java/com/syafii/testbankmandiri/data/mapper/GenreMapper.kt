package com.syafii.testbankmandiri.data.mapper
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import com.syafii.testbankmandiri.data.payload.response.GenreResponse
import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.utils.orNull
import com.syafii.testbankmandiri.utils.orNullListNot

fun GenreResponse.toGenre() : GenreModel {
    return GenreModel(
        genre = genres.orNullListNot { it.toItemGenre() }
    )
}

fun GenreResponse.GenreItemResponse.toItemGenre(): GenreModel.GenreItemModel {
    return GenreModel.GenreItemModel(
        id = id.orNull(),
        name = name.orNull(),
    )
}