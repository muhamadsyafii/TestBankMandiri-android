package com.syafii.testbankmandiri.data.payload.response
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres : List<GenreItemResponse>? = null
){
    data class GenreItemResponse(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("name")
        val name: String? = null,
    )
}