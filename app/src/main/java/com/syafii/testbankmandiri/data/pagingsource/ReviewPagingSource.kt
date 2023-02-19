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
import com.syafii.testbankmandiri.data.mapper.toReview
import com.syafii.testbankmandiri.data.remote.ApiService
import com.syafii.testbankmandiri.domain.model.ReviewModel
import retrofit2.HttpException

class ReviewPagingSource(
    private val service: ApiService,
    private val movieId: Int,
    private val isPaging: Boolean,

    ) : PagingSource<Int, ReviewModel>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewModel>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewModel> {
        val pageIndex = params.key ?: 1

        try {
            val response = service.getListMovieReview(page = pageIndex, movieId = movieId)
            return if (response.isSuccessful) {
                val dataResponse = response.body()
                val result = dataResponse?.results?.asSequence()?.map { it.toReview() }?.toList()
                    ?: emptyList()

                // limit only 500 max page. because cant load more than 500 page (response error from moviedb)
                val nextKey =
                    if (dataResponse?.results.isNullOrEmpty() || pageIndex == 500) null else pageIndex + 1

                LoadResult.Page(
                    data = result,
                    prevKey = if (pageIndex == 1) null else pageIndex,
                    nextKey = if (isPaging) nextKey else null
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