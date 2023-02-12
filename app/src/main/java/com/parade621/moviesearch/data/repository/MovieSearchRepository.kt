package com.parade621.moviesearch.data.repository

import androidx.lifecycle.LiveData
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.data.model.SearchResponse
import retrofit2.Response

interface MovieSearchRepository {

    suspend fun saerchMovie(
        query: String,
    ): Response<SearchResponse>

    // Room Dao를 조작하기 위한 메서드
    suspend fun insertQuery(query: RecentSearch)
    suspend fun deleteQuery(query: RecentSearch)
    fun getAll(): LiveData<List<RecentSearch>>

}