package com.syafii.testbankmandiri.data.remote
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.syafii.testbankmandiri.BankMandiriApp
import com.syafii.testbankmandiri.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkProvider {

    @Provides
    @Singleton
    fun getOkhttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)

        /** HTTP Logging and Chucker*/
        builder.apply {
            if (BuildConfig.DEBUG){
                addInterceptor(getHttpLoggingInterceptor())
                addInterceptor(getChuckerInterceptor())
            }
        }

        builder.addInterceptor { chain ->
            val mOriRequest: Request = chain.request()
            val token = BuildConfig.TOKEN_MOVIE
            val request: Request =
                mOriRequest.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .addHeader("Accept", "application/json")
                    .method(mOriRequest.method, mOriRequest.body)
                    .build()
            chain.proceed(request)
        }
        return builder.build()
    }

    @Provides
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun getChuckerInterceptor(): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(BankMandiriApp.context).build()
    }


}