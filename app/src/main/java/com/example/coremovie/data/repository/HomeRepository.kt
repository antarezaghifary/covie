package com.example.coremovie.data.repository

import com.example.coremovie.data.model.popular.PopularResponse
import com.example.coremovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPopularMovies(): Flow<Resource<PopularResponse>>
}