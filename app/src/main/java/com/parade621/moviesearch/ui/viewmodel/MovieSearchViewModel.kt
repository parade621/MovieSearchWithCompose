package com.parade621.moviesearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.data.model.SearchResponse
import com.parade621.moviesearch.data.repository.MovieSearchRepository
import kotlinx.coroutines.Dispatchers
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
        val response = movieSearchRepository.saerchMovie(query)
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
    // 함수 하나 만들어서 역순 정렬ㄱ
    // 10개씩 정렬까지만 만들면 대충 기능 구현은 완료

    fun clearRecentSearch() {
        _recentSearch.value = null
    }
}