package com.parade621.moviesearch.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(query: RecentSearch)

    @Delete
    suspend fun deleteQuery(query: RecentSearch)

    @Query("SELECT * FROM querys")
    fun getAll(): LiveData<List<RecentSearch>>
}