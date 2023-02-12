package com.parade621.moviesearch.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.parade621.moviesearch.data.api.MovieSearchApi
import com.parade621.moviesearch.data.model.Movie
import com.parade621.moviesearch.data.model.SearchResponse
import com.parade621.moviesearch.util.Constants.PAGING_SIZE
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MovieSearchPagingSource(
    private val api: MovieSearchApi,
    private val query: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(PAGING_SIZE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(PAGING_SIZE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber: Int = params.key ?: STARTING_INDEX
            val response: Response<SearchResponse> =
                api.searchMovies(query, params.loadSize, pageNumber)
            val endOfPaginationReached: Boolean =
                (response.body()?.start!! + response.body()?.display!! == response.body()?.total!!)
            Log.d("PagingSource:", "${response.body()?.start} and ${response.body()?.display!!}")
            Log.d("이름 페이징소스", "${response.body()?.items.toString()}")

            val data: List<Movie> = response.body()?.items!!
            val prevkey: Int? = if (pageNumber == STARTING_INDEX) null else pageNumber - PAGING_SIZE
            val nextKey: Int? = if (endOfPaginationReached) {
                null
            } else {
                pageNumber + (params.loadSize / PAGING_SIZE)
            }
            LoadResult.Page(
                data = data,
                prevKey = prevkey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val STARTING_INDEX = 1
    }
}