package com.syafii.testbankmandiri.utils
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

sealed class StateResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : StateResponse<T>()
    data class Failed(val message: String) : StateResponse<Nothing>()
}

/**
 * extension function
 * `true` if [ResultData] is of type [Success] & holds non-null [Success.data].
 */

val StateResponse<*>.succeeded
    get() = this is StateResponse.Success
