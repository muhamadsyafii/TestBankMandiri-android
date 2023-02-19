/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.data.mapper

import com.syafii.testbankmandiri.data.payload.response.VideoResponse
import com.syafii.testbankmandiri.domain.model.VideoModel
import com.syafii.testbankmandiri.utils.orNull
import com.syafii.testbankmandiri.utils.orNullListNot


fun VideoResponse.toVideo() : VideoModel {
    return VideoModel(
        id = id.orNull(),
        results = results.orNullListNot { it.toVideoItem() }
    )
}

fun VideoResponse.VideoItemResponse.toVideoItem() : VideoModel.VideoItemModel {
    return VideoModel.VideoItemModel(
        site = site.orNull(),
        size = size.orNull(),
        iso31661 = iso31661.orNull(),
        name = name.orNull(),
        official = official.orNull(),
        id = id.orNull(),
        type = type.orNull(),
        publishedAt = publishedAt.orNull(),
        iso6391 = iso6391.orNull(),
        key = key.orNull(),
    )
}