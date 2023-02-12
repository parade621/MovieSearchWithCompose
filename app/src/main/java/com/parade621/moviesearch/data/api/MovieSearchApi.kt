package com.parade621.moviesearch.data.api

import com.parade621.moviesearch.data.model.SearchResponse
import com.parade621.moviesearch.util.Constants.CLIENT_ID
import com.parade621.moviesearch.util.Constants.CLIENT_SECRET
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieSearchApi {
    @Headers(
        "X-Naver-Client-Id: $CLIENT_ID",
        "X-Naver-Client-Secret: $CLIENT_SECRET"
    )
    @GET("v1/search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): Response<SearchResponse>
}