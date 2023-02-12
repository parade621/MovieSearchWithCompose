package com.parade621.moviesearch.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.parade621.moviesearch.data.api.RetrofitInstance.api
import com.parade621.moviesearch.data.db.AppDatabase
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.data.model.SearchResponse
import retrofit2.Response

class MovieSearchRepositoryImpl(
    private val db: AppDatabase
) : MovieSearchRepository {

    override suspend fun saerchMovie(
        query: String
    ): Response<SearchResponse> {
        Log.d("see query in?:", query.toString())
        return api.searchMovies(query)
    }

    override suspend fun insertQuery(query: RecentSearch) {
        db.movieSearchDao().insertQuery(query)
    }

    override suspend fun deleteQuery(query: RecentSearch) {
        db.movieSearchDao().deleteQuery(query)
    }

    override fun getAll(): LiveData<List<RecentSearch>> {
        return db.movieSearchDao().getAll()
    }


}