package ru.dk.movies.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dk.movies.R
import ru.dk.movies.databinding.FragmentMoveisBinding

class MoviesFragment : Fragment(R.layout.fragment_moveis) {

    private var _binding: FragmentMoveisBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModel()
    private val adapter: MovieAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoveisBinding.bind(view)



        binding.apply {
            rvMovies.setHasFixedSize(true)
            rvMovies.adapter = adapter
        }
        viewModel.getMovies()
        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}