package com.parade621.moviesearch.ui.data.api

import com.parade621.moviesearch.ui.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val api: MovieSearchApi by lazy {
        retrofit.create(MovieSearchApi::class.java)
    }
}