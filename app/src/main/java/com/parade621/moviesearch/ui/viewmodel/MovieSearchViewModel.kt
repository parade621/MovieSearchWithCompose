package com.parade621.moviesearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.data.model.Movie
import com.parade621.moviesearch.data.model.SearchResponse
import com.parade621.moviesearch.data.repository.MovieSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieSearchViewModel(
    private val movieSearchRepository: MovieSearchRepository
) : ViewModel() {

    //Api
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    private val _recentSearch = MutableLiveData<String?>(null)
    val recentSearch: LiveData<String?> get() = _recentSearch!!


    // Coroutine
    fun searchMovies(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = movieSearchRepository.saerchMovie(query, 10, 1)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }

    fun setRecentSearch(query: String) {
        _recentSearch.value = query
    }

    // Room
    fun saveQuery(query: RecentSearch) = viewModelScope.launch(Dispatchers.IO) {
        movieSearchRepository.insertQuery(query)
    }

    fun deleteQuery(query: RecentSearch) = viewModelScope.launch(Dispatchers.IO) {
        movieSearchRepository.deleteQuery(query)
    }

    val allQuery: LiveData<List<RecentSearch>> = movieSearchRepository.getAll()

    fun clearRecentSearch() {
        _recentSearch.value = null
    }

    private val _searchPagingResult = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val searchPagingResult: StateFlow<PagingData<Movie>> = _searchPagingResult.asStateFlow()

    fun searchMoviePaging(query: String) {
        viewModelScope.launch {
            movieSearchRepository.searchMoviePaging(query)
                .cachedIn(viewModelScope)
                .collect {
                    _searchPagingResult.value = it
                }
        }
    }
}