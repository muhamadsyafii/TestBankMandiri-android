/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.domain.model

data class VideoModel(
    val id: Int,
    val results: List<VideoItemModel>
){
    data class VideoItemModel(
        val site: String,
        val size: Int,
        val iso31661: String,
        val name: String,
        val official: Boolean,
        val id: String,
        val type: String,
        val publishedAt: String,
        val iso6391: String,
        val key: String
    )
}