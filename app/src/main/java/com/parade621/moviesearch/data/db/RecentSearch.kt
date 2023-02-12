package com.parade621.moviesearch.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "querys")
data class RecentSearch(
    @PrimaryKey
    @ColumnInfo(name = "recentQuery")
    val query: String
)
