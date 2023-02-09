package com.parade621.moviesearch.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface MovieSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(query: String)

    @Delete
    suspend fun deleteQuery(query: String)
}