package com.parade621.moviesearch.ui.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.parade621.moviesearch.databinding.FragmentSearchBinding
import com.parade621.moviesearch.ui.adapter.MovieSearchAdapter
import com.parade621.moviesearch.ui.viewmodel.MovieSearchViewModel
import com.parade621.moviesearch.util.Constants.SEARCH_TIME_DELAY

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieSearchViewModel: MovieSearchViewModel
    private lateinit var movieSearchAdapter: MovieSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieSearchViewModel = (activity as MainActivity).movieSearchViewModel
        setupRecyclerView()
        searchBooks()

        movieSearchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val movies = response.items
            movieSearchAdapter.submitList(movies)
        }

    }

    private fun setupRecyclerView() {
        movieSearchAdapter = MovieSearchAdapter()
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = movieSearchAdapter
        }
    }

    private fun searchBooks() {
        var startTime = System.currentTimeMillis()
        var endTime: Long

        binding.etSearch.addTextChangedListener { text: Editable? ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= SEARCH_TIME_DELAY) {
                text?.let {
                    val query = it.toString().trim()
                    if (query.isNotEmpty()) {
                        movieSearchViewModel.searchMovies(query)
                        Log.d("see Log:", query.toString())
                    }
                }
            }
            startTime = endTime
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}