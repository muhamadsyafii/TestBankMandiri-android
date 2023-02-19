/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.data.payload.response

import com.google.gson.annotations.SerializedName

data class PagingResponse<DATA>(
    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null,

    @SerializedName("results")
    val results: DATA? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null

)