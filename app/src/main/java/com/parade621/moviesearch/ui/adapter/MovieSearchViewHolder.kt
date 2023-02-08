package com.parade621.moviesearch.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.parade621.moviesearch.data.model.Movie
import com.parade621.moviesearch.databinding.ItemMoviePreviewBinding

class MovieSearchViewHolder(
    private val binding: ItemMoviePreviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        itemView.apply {
            binding.ivArtivleIamge.load(movie.image)
            binding.tvTitle.text = "제목: ${movie.title}"
            binding.tvRelease.text = "출시: ${movie.pubDate}"
            binding.tvGrade.text = "평점: ${movie.userRating}"
        }
    }

}