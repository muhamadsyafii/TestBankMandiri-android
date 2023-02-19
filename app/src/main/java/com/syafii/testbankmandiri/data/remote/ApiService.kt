package com.syafii.testbankmandiri.data.remote
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import com.syafii.testbankmandiri.data.payload.response.GenreResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getListGenre(): Response<GenreResponse>

}