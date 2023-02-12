package com.parade621.moviesearch.data.api

import com.parade621.moviesearch.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    // 레트로핏을 통해 네이버 영화 api로부터 json정보를 취득합니다.
    // 기능이 원활하게 수행되는지 LogCat에서 확인하기 위해 okHttp로 로그 정보를 확인합니다.

    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    val api: MovieSearchApi by lazy {
        retrofit.create(MovieSearchApi::class.java)
    }
}