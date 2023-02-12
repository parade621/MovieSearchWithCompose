package com.parade621.moviesearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.parade621.moviesearch.data.model.Movie
import com.parade621.moviesearch.databinding.ItemMoviePreviewBinding

class MovieSearchPagingAdapter :
    PagingDataAdapter<Movie, MovieSearchViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        return MovieSearchViewHolder(
            ItemMoviePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        val pagedMovie: Movie? = getItem(position)
        pagedMovie?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(movie) }
            }
        }
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null
    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val MovieDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.subtitle == newItem.subtitle
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}