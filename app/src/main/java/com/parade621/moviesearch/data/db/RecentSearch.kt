package com.parade621.moviesearch.data.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class RecentSearch(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "recent_search") val recentSearch: String
)
