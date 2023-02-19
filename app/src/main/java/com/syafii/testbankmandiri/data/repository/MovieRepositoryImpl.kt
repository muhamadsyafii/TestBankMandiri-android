package com.syafii.testbankmandiri.data.repository
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import com.syafii.testbankmandiri.data.mapper.toGenre
import com.syafii.testbankmandiri.data.remote.ApiService
import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.utils.ErrorUtil
import com.syafii.testbankmandiri.utils.StateResponse
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val service: ApiService) : MovieRepository {
    override suspend fun getListGenre(): StateResponse<GenreModel> {
        return try {
            val response = service.getListGenre()
            if (response.isSuccessful) {
                val data = response.body()?.toGenre() ?: GenreModel()
                StateResponse.Success(data = data)
            } else {
                val message = ErrorUtil.getErrorMessage(response.errorBody()?.string() ?: "")
                StateResponse.Failed(message)
            }

        } catch (e: Exception) {
            StateResponse.Failed(e.localizedMessage ?: "Error Exception")
        }
    }
}