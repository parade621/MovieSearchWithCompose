package com.parade621.moviesearch.data.repository

import android.util.Log
import com.parade621.moviesearch.data.api.RetrofitInstance.api
import com.parade621.moviesearch.data.model.SearchResponse
import retrofit2.Response

class MovieSearchRepositoryImpl : MovieSearchRepository {
    override suspend fun saerchMovie(
        query: String
    ): Response<SearchResponse> {
        Log.d("see query in?:", query.toString())
        return api.searchMovies(query)
    }
}