package com.example.coremovie.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coremovie.domain.interactor.HomeInteractor
import com.example.coremovie.domain.model.popular.PopularResponse
import com.example.coremovie.util.ERROR
import com.example.coremovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeInteractor: HomeInteractor
) : ViewModel() {
    private val _popularMoviesState = MutableLiveData<Resource<PopularResponse>>()
    val popularMoviesState: LiveData<Resource<PopularResponse>> = _popularMoviesState
    fun getPopularMovies() {

        viewModelScope.launch {
            _popularMoviesState.value = Resource.Loading()
            try {
                homeInteractor.fetchPopularMovies().distinctUntilChanged().collectLatest { result ->
                    Log.d("HomeViewModel", "Received result: ${result.data}")
                    if (result.data != null) {
                        _popularMoviesState.value = Resource.Success(result.data)
                    }
                }
            } catch (e: Exception) {
                _popularMoviesState.value = Resource.Error(
                    error = ERROR.General,
                    errMsg = "Failed to fetch popular movies: ${e.message}"
                )
            }
        }
    }
}