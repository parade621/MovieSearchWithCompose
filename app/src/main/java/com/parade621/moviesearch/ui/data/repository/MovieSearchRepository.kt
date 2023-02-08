package com.parade621.moviesearch.ui.data.repository

import com.parade621.moviesearch.ui.data.model.SearchResponse
import retrofit2.Response

interface MovieSearchRepository {

    suspend fun saerchMovie(
        query: String,
        display: Int,
        start: Int,
        genre: String,
        country: String,
        yearfrom: Int,
        yearto: Int,
    ): Response<SearchResponse>
}