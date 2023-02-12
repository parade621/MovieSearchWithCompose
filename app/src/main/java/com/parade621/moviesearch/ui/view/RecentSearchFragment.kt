package com.parade621.moviesearch.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.parade621.moviesearch.databinding.FragmentRecentSearchBinding
import com.parade621.moviesearch.ui.adapter.RecentSearchAdapter
import com.parade621.moviesearch.ui.viewmodel.MovieSearchViewModel
import com.parade621.moviesearch.util.Constants.RECENT_SEARCH_PREVIEW

class RecentSearchFragment : Fragment() {

    private var _binding: FragmentRecentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieSearchViewModel: MovieSearchViewModel
    private lateinit var recentSearchAdapter: RecentSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recentSearchFragment = this@RecentSearchFragment
        }

        movieSearchViewModel = (activity as MainActivity).movieSearchViewModel

        /**
         * 검색 기록을 최신 순서로 표시하기 위해 db에서 가져온 데이터List를 reversed합니다.
         */
        movieSearchViewModel.allQuery.observe(viewLifecycleOwner) {
            if (it.size > RECENT_SEARCH_PREVIEW) {
                recentSearchAdapter.submitList(it.reversed().slice(0..9))
            } else {
                recentSearchAdapter.submitList(it.reversed())
            }
        }

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        recentSearchAdapter = RecentSearchAdapter(movieSearchViewModel)
        binding.rvRecentSearch.apply {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(requireContext(), 4)
            adapter = recentSearchAdapter
        }
        recentSearchAdapter.setOnItemClickListener {
            movieSearchViewModel.setRecentSearch(it.query)
            movieSearchViewModel.deleteQuery(it)
            val action: NavDirections =
                RecentSearchFragmentDirections.actionRecentSearchFragmentToSearchFragment()
            findNavController().navigate(action)
            onDestroyView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}