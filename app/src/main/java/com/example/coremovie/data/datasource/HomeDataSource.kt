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
    //code
    override suspend fun getPopularMovies(): Flow<Resource<PopularResponse>> = flow {
        emit(Resource.Loading()) // Emit loading state

        // Make the API call
        val response = api.getDataPopular(apiKey = BuildConfig.API_KEY, language = "en-US")

        try {
            // Check if the response is successful
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(Resource.Success(data)) // Emit successful response with data
                } ?: emit(Resource.Error(error = ERROR.General, "Empty response body"))
            } else {
                emit(
                    Resource.Error(
                        error = ERROR.General,
                        "Error: ${response.code()} - ${response.message()}"
                    )
                )
            }
        } catch (e: Exception) {
            // Handle exceptions (like network errors)
            emit(Resource.Error(error = ERROR.General, "Exception: ${e.message}"))
        }
    }
}