package com.parade621.moviesearch.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.parade621.moviesearch.data.repository.MovieSearchRepositoryImpl
import com.parade621.moviesearch.databinding.ActivityMainBinding
import com.parade621.moviesearch.ui.viewmodel.MovieSearchViewModel
import com.parade621.moviesearch.ui.viewmodel.MovieSearchViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var movieSearchViewModel: MovieSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movieSearchRepository = MovieSearchRepositoryImpl()
        val factory = MovieSearchViewModelProviderFactory(movieSearchRepository)
        movieSearchViewModel = ViewModelProvider(this, factory)[MovieSearchViewModel::class.java]

    }
}