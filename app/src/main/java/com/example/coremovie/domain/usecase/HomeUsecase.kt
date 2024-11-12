package com.example.coremovie.domain.usecase

import com.example.coremovie.domain.model.popular.PopularResponse
import com.example.coremovie.util.Resource
import kotlinx.coroutines.flow.Flow


interface HomeUsecase {
    suspend fun fetchPopularMovies(): Flow<Resource<PopularResponse>>
}