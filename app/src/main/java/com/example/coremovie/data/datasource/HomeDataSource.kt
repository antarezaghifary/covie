package com.example.coremovie.data.datasource

import com.example.coremovie.BuildConfig
import com.example.coremovie.data.model.popular.PopularResponse
import com.example.coremovie.data.remote.api.ApiService
import com.example.coremovie.data.repository.HomeRepository
import com.example.coremovie.util.ERROR
import com.example.coremovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val api: ApiService
) : HomeRepository {
    override suspend fun getPopularMovies(): Flow<Resource<PopularResponse>> = flow {
        emit(Resource.Loading())
        val response = api.getDataPopular(apiKey = BuildConfig.API_KEY, language = "en-US")
        try {
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(error = ERROR.General, "Exception: ${e.message}"))
        }
    }
}