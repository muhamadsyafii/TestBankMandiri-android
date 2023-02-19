/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.utils

sealed class StateUi<out T : Any> {
    object Loading : StateUi<Nothing>()
    data class Success<out S : Any>(val message: String, val data: S) : StateUi<S>()
    data class Failed(val message: String) : StateUi<Nothing>()
}
