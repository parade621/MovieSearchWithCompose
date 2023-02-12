package com.parade621.moviesearch.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.parade621.moviesearch.R
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.databinding.FragmentSearchBinding
import com.parade621.moviesearch.ui.adapter.MovieSearchPagingAdapter
import com.parade621.moviesearch.ui.viewmodel.MovieSearchViewModel
import com.parade621.moviesearch.util.collectLatestStateFlow

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieSearchViewModel: MovieSearchViewModel
    private lateinit var movieSearchAdapter: MovieSearchPagingAdapter


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
        binding.apply {
            searchFragment = this@SearchFragment
        }
        movieSearchViewModel = (activity as MainActivity).movieSearchViewModel

        if (!movieSearchViewModel.recentSearch.value.isNullOrEmpty()) {
            binding.etSearch.setText(movieSearchViewModel.recentSearch.value.toString())
            movieSearchViewModel.clearRecentSearch()
            searchMovies()
        }
        setupRecyclerView()
        collectLatestStateFlow(movieSearchViewModel.searchPagingResult) {
            movieSearchAdapter.submitData(it)
        }
    }

    private fun setupRecyclerView() {
        movieSearchAdapter = MovieSearchPagingAdapter()
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
        // ItemClick
        movieSearchAdapter.setOnItemClickListener {
            val action: NavDirections =
                SearchFragmentDirections.actionSearchFragmentToMoviewFragment(it)
            findNavController().navigate(action)
        }
    }

    fun showRecentSearch() {
        findNavController().navigate(R.id.action_searchFragment_to_recentSearchFragment)
    }

    fun searchMovies() {
        val query = binding.etSearch.text.toString()
        Log.d("이름: 입력", query)
        movieSearchViewModel.searchMoviePaging(query)
        movieSearchViewModel.saveQuery(RecentSearch(query))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}