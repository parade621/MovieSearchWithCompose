package com.parade621.moviesearch.util

import com.parade621.moviesearch.BuildConfig

object Constants {
    const val BASE_URL = "https://openapi.naver.com/"
    const val CLIENT_ID: String = "VQ4b2kKCDHykbztZf75r"
    const val CLIENT_SECRET: String = BuildConfig.moviewApiSecretKey
    const val SEARCH_TIME_DELAY = 100L
    const val RECENT_SEARCH_PREVIEW = 10
}