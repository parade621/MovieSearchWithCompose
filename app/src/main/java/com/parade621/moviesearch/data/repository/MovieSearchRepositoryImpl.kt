package com.parade621.moviesearch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.parade621.moviesearch.data.api.RetrofitInstance.api
import com.parade621.moviesearch.data.db.AppDatabase
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.data.model.Movie
import com.parade621.moviesearch.util.Constants.PAGING_SIZE
import kotlinx.coroutines.flow.Flow


class MovieSearchRepositoryImpl(
    private val db: AppDatabase,
) : MovieSearchRepository {

    override suspend fun insertQuery(query: RecentSearch) {
        db.movieSearchDao().insertQuery(query)
    }

    override suspend fun deleteQuery(query: RecentSearch) {
        db.movieSearchDao().deleteQuery(query)
    }

    override fun getAll(): LiveData<List<RecentSearch>> {
        return db.movieSearchDao().getAll()
    }

    // Paging
    override fun searchMoviePaging(query: String): Flow<PagingData<Movie>> {
        val pagingSourceFactory: () -> MovieSearchPagingSource = {
            MovieSearchPagingSource(api, query)
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = PAGING_SIZE * 3
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


}