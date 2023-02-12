package com.parade621.moviesearch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.data.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * 레트로핏, Room의 원활한 기능 수행읋 위한 Repository입니다.
 */

interface MovieSearchRepository {

    // Room Dao를 조작하기 위한 메서드
    // insert와 delete는 SQLite의 컴파일 시간이 불확실하기 때문에 코루틴으로 작성하였습니다.

    suspend fun insertQuery(query: RecentSearch)
    suspend fun deleteQuery(query: RecentSearch)
    fun getAll(): LiveData<List<RecentSearch>>

    fun searchMoviePaging(
        query: String
    ): Flow<PagingData<Movie>>

}