package com.example.coremovie.presentation.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.coremovie.databinding.FragmentHomeBinding
import com.example.coremovie.domain.model.popular.ResultsItem
import com.example.coremovie.presentation.base.BaseFragment
import com.example.coremovie.presentation.home.viewmodel.HomeViewModel
import com.example.coremovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initStateApi()
        initUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPopularMovies()
    }

    override fun initOnClick() {

    }

    override fun collectState() {

    }

    private fun initUI() {
        binding.lTIname.apply {
            labelText = "Nama Pengguna"
            val inputText = inputText
        }

        binding.customTopNav.apply {
            setTitle("Menu")
            setOnBackClickListener {
            }

            setOnMenuClickListener {
            }
        }

        binding.customPasswordInput.apply {
            setHintText("Enter your PIN")

            setPasswordText("1234")

            val password = getPassword()

            setOnPasswordVisibilityToggleClickListener {

            }
        }

        binding.customUsernameInput.apply {
            setLeftIcon(null)
            setRightIcon(null)
        }

    }

    private fun initStateApi() {
        viewModel.popularMoviesState.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoadingIndicator()
                }

                is Resource.Success -> {
                    resource.data?.let { popularResponse ->
                        showPopularMovies(popularResponse.results)
                    }
                }

                is Resource.Error -> {
                    showError(resource.errMsg)
                }
            }
        }
    }

    private fun showLoadingIndicator() {
        Toast.makeText(context, "Loading ...", Toast.LENGTH_SHORT).show()
    }

    private fun showPopularMovies(popularResponse: List<ResultsItem>) {
        Toast.makeText(context, "$popularResponse", Toast.LENGTH_SHORT).show()
    }

    private fun showError(message: String?) {
        Toast.makeText(context, message ?: "Unknown error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}