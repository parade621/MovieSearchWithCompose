package com.parade621.moviesearch.ui.data.repository

import com.parade621.moviesearch.ui.data.api.RetrofitInstance.api
import com.parade621.moviesearch.ui.data.model.SearchResponse
import retrofit2.Response

class MovieSearchRepositoryImpl : MovieSearchRepository {
    override suspend fun saerchMovie(
        query: String,
        display: Int,
        start: Int,
        genre: String,
        country: String,
        yearfrom: Int,
        yearto: Int
    ): Response<SearchResponse> {
        return api.searchMovies(query, display, start, genre, country, yearfrom, yearto)
    }
}