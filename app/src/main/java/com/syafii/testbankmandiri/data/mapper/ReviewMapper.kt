/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.data.mapper

import com.syafii.testbankmandiri.data.payload.response.ReviewResponse
import com.syafii.testbankmandiri.domain.model.ReviewModel
import com.syafii.testbankmandiri.utils.orNull
import com.syafii.testbankmandiri.utils.orNullObject

fun ReviewResponse.toReview() : ReviewModel {
    return ReviewModel(
        authorDetails = authorDetails.orNullObject({it.toAuthorDetail()}, ReviewModel.AuthorDetailModel()),
        updatedAt = updatedAt.orNull(),
        author = author.orNull(),
        createdAt = createdAt.orNull(),
        id = id.orNull(),
        content = content.orNull(),
        url = url.orNull(),
    )
}

fun ReviewResponse.AuthorDetailResponse.toAuthorDetail() : ReviewModel.AuthorDetailModel{
    return ReviewModel.AuthorDetailModel(
        avatarPath = avatarPath.orNull(),
        name = name.orNull(),
        rating = rating.orNull(),
        username = username.orNull()
    )
}