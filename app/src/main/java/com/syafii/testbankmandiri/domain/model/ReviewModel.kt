/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.domain.model

import com.syafii.testbankmandiri.BuildConfig

data class ReviewModel(
    val authorDetails: AuthorDetailModel,
    val updatedAt: String = "",
    val author: String = "",
    val createdAt: String = "",
    val id: String = "",
    val content: String = "",
    val url: String = ""
){
    data class AuthorDetailModel(
        val avatarPath: String = "",
        val name: String = "",
        val rating: Double = 0.0,
        val username: String = ""
    ){
        val avatarPathClean = BuildConfig.BASE_URL_IMAGE + avatarPath
    }
}
