package com.parade621.moviesearch.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.databinding.RecentQueryPreviewBinding
import com.parade621.moviesearch.ui.viewmodel.MovieSearchViewModel


class RecentSearchViewHolder(
    private val binding: RecentQueryPreviewBinding,
    private val viewModel: MovieSearchViewModel
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.ivDelete.setOnClickListener {
            viewModel.deleteQuery(RecentSearch(binding.tvQuery.text.toString()))
        }
    }

    fun bind(query: RecentSearch) {
        itemView.apply {
            binding.tvQuery.text = query.query
        }
    }

}