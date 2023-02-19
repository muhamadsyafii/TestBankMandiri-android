package com.syafii.testbankmandiri.module
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import com.syafii.testbankmandiri.data.remote.ApiService
import com.syafii.testbankmandiri.data.repository.MovieRepository
import com.syafii.testbankmandiri.data.repository.MovieRepositoryImpl
import com.syafii.testbankmandiri.domain.usecase.MovieUseCase
import com.syafii.testbankmandiri.domain.usecase.MovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object BankMandiriModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository =
        repositoryImpl

    @Provides
    fun provideMovieUseCase(usecaseImpl: MovieUseCaseImpl): MovieUseCase = usecaseImpl

}