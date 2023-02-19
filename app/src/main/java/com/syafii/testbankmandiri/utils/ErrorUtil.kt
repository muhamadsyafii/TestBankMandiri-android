package com.syafii.testbankmandiri.utils
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import org.json.JSONObject

object ErrorUtil {
    fun getErrorMessage(errorResponse: String): String {
        return try {
            val responseError = JSONObject(errorResponse)
            val errorMessage = responseError.get("error")
            JSONObject(errorMessage.toString()).get("errors").toString()
        } catch (e: Exception) {
            "Error Not found"
        }

    }
}
