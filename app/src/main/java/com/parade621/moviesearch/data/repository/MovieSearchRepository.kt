package com.parade621.moviesearch.data.repository

import com.parade621.moviesearch.data.model.SearchResponse
import retrofit2.Response

interface MovieSearchRepository {

    suspend fun saerchMovie(
        query: String,
    ): Response<SearchResponse>
}