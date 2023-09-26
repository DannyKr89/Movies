package ru.dk.movies.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dk.movies.R
import ru.dk.movies.data.model.MovieDTO
import ru.dk.movies.databinding.FragmentMoveisBinding

class MoviesFragment : Fragment(R.layout.fragment_moveis) {

    private var _binding: FragmentMoveisBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModel()
    private val adapter: MovieAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoveisBinding.bind(view)

        initView()
        initViewModel()
    }

    private fun initView() {
        binding.apply {
            rvMovies.setHasFixedSize(true)
            rvMovies.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MoviesLoadStateAdapter { adapter.retry() },
                footer = MoviesLoadStateAdapter { adapter.retry() }
            )
        }
    }

    private fun initViewModel() {
        viewModel.getMovies().observe(viewLifecycleOwner) {
            showData(it)
        }
    }

    private fun showData(pagingData: PagingData<MovieDTO>) {
        adapter.submitData(lifecycle, pagingData)
        adapter.listener = {
            findNavController().navigate(
                R.id.action_moviesFragment_to_movieDetailFragment, Bundle().apply
                {
                    putInt("id", it.id)
                })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}