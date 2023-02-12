package com.parade621.moviesearch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.data.model.Movie
import com.parade621.moviesearch.data.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * 레트로핏, Room의 원활한 기능 수행읋 위한 Repository입니다.
 */

interface MovieSearchRepository {

    suspend fun saerchMovie(
        query: String,
        display: Int,
        start: Int
    ): Response<SearchResponse>

    // Room Dao를 조작하기 위한 메서드
    // insert와 delete는
    suspend fun insertQuery(query: RecentSearch)
    suspend fun deleteQuery(query: RecentSearch)
    fun getAll(): LiveData<List<RecentSearch>>

    fun searchMoviePaging(
        query: String
    ): Flow<PagingData<Movie>>

}