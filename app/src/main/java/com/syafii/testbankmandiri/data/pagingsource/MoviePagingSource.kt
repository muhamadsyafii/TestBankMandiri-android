/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.syafii.testbankmandiri.data.mapper.toMovie
import com.syafii.testbankmandiri.data.payload.response.PagingResponse
import com.syafii.testbankmandiri.data.remote.ApiService
import com.syafii.testbankmandiri.domain.model.MovieModel
import retrofit2.HttpException

class MoviePagingSource(private val service: ApiService, private val genreIds: String) : PagingSource<Int, MovieModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val pageIndex = params.key ?: 1

        try {
            val response = service.getMovieByGenre(page = pageIndex, genreIds = genreIds)
            return if (response.isSuccessful) {
                val dataResponse = response.body()
                val result = dataResponse?.results?.asSequence()?.map { it.toMovie() }?.toList() ?: emptyList()
                // limit only 500 max page. because cant load more than 500 page (response error from moviedb)
                val nextKey =
                    if (dataResponse?.results.isNullOrEmpty() || pageIndex == 500) null else pageIndex + 1

                LoadResult.Page(
                    data = result,
                    prevKey = if (pageIndex == 1) null else pageIndex,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception(Throwable("Failed get data")))
            }
        } catch (error: Exception) {
            return LoadResult.Error(error)
        } catch (error: HttpException) {
            return LoadResult.Error(error)
        }
}
}