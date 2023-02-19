package com.syafii.testbankmandiri
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BankMandiriApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: BankMandiriApp
            private set
    }
}