package com.parade621.moviesearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.parade621.moviesearch.data.db.RecentSearch
import com.parade621.moviesearch.databinding.RecentQueryPreviewBinding
import com.parade621.moviesearch.ui.viewmodel.MovieSearchViewModel

/**
 * 최근 검색 기록을 위한 RecyclerView Adapter입니다.
 * RecentSearchFragment에서 바로 검색 기록을 삭제 가능하도록 하기 위해 viewModel을 파라미터로 받습니다.
 */

class RecentSearchAdapter(private val viewModel: MovieSearchViewModel) :
    ListAdapter<RecentSearch, RecentSearchViewHolder>(SearchDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        return RecentSearchViewHolder(
            RecentQueryPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            viewModel
        )
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val recentSearch = currentList[position]
        holder.bind(recentSearch)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(recentSearch)
            }
        }
    }

    private var onItemClickListener: ((RecentSearch) -> Unit)? = null

    fun setOnItemClickListener(listener: (RecentSearch) -> Unit) {
        onItemClickListener = listener
    }


    companion object {
        private val SearchDiffCallback = object : DiffUtil.ItemCallback<RecentSearch>() {
            override fun areItemsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
                return oldItem.query == newItem.query
            }

            override fun areContentsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
                return oldItem == newItem
            }
        }
    }

}