package ru.dk.movies.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.dk.movies.R
import ru.dk.movies.data.model.MovieDTO
import ru.dk.movies.databinding.ItemMovieBinding

class MovieAdapter : ListAdapter<MovieDTO, MovieAdapter.MovieViewHolder>(COMPARATOR) {
    class MovieViewHolder(private val binding: ItemMovieBinding) : ViewHolder(binding.root) {

        fun bind(movie: MovieDTO) {
            binding.apply {

                Glide.with(itemView)
                    .load(movie.poster.url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_placeholder)
                    .into(ivMoviePoster)
                tvMovieName.text = movie.name
                tvMovieYear.text = movie.year.toString()
                tvMovieRating.text = String.format("%.1f", movie.rating.kp)

            }
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<MovieDTO>() {
            override fun areItemsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }
}