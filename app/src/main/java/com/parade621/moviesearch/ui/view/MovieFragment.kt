package com.parade621.moviesearch.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.parade621.moviesearch.data.model.Movie
import com.parade621.moviesearch.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    // Webview setting
    private val args: MovieFragmentArgs by navArgs<MovieFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: Movie = args.movie
        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            Log.d("why http?:", movie.link)
            loadUrl(movie.link)

        }
    }

    override fun onPause() {
        binding.webview.onPause()
        super.onPause()
    }

    override fun onResume() {
        binding.webview.onResume()
        super.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}