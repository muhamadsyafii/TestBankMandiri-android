package com.syafii.testbankmandiri.data.repository
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.utils.StateResponse

interface MovieRepository {
    suspend fun getListGenre(): StateResponse<GenreModel>
}