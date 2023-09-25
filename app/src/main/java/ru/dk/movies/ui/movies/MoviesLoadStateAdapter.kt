package ru.dk.movies.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.dk.movies.databinding.LoadStateBinding

class MoviesLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MoviesLoadStateAdapter.MoviesStateViewHolder>() {


    inner class MoviesStateViewHolder(private val binding: LoadStateBinding) :
        ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                btnRetry.setOnClickListener {
                    retry.invoke()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MoviesStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MoviesStateViewHolder {
        val binding = LoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesStateViewHolder(binding)
    }
}