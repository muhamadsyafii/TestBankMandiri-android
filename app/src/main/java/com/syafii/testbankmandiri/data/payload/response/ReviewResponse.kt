/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.data.payload.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("author_details")
    val authorDetails: AuthorDetailResponse?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("url")
    val url: String?
){
    data class AuthorDetailResponse(
        @SerializedName("avatar_path")
        val avatarPath: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("rating")
        val rating: Double?,
        @SerializedName("username")
        val username: String?
    )
}