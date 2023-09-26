package ru.dk.movies.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dk.movies.R
import ru.dk.movies.data.model.MovieDetails
import ru.dk.movies.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailBinding.bind(view)

        initView()
        initViewModel()
    }

    private fun initView() {
        binding.apply {

        }
    }

    private fun initViewModel() {
        arguments?.let {
            val id = it.getInt("id")
            viewModel.getMovieDetails(id).observe(viewLifecycleOwner) {
                showData(it)
            }
        }
    }

    private fun showData(movieDetails: MovieDetails) {
        binding.apply {
            Glide.with(root)
                .load(movieDetails.poster.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivMovieDetailPoster)
            tvMovieDetailName.text = movieDetails.name
            tvMovieDetailYear.text = movieDetails.year.toString()
            tvMovieDetailRating.text = String.format("%.1f", movieDetails.rating.kp)
            tvMovieDetailDescription.text = movieDetails.description
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}