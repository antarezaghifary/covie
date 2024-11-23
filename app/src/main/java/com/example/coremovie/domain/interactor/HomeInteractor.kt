package com.example.coremovie.domain.interactor

import android.content.Context
import com.example.coremovie.data.repository.HomeRepository
import com.example.coremovie.domain.model.popular.PopularResponse
import com.example.coremovie.domain.model.popular.toDomain
import com.example.coremovie.domain.usecase.HomeUsecase
import com.example.coremovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val context: Context, private val repo: HomeRepository
) : HomeUsecase {
    override suspend fun fetchPopularMovies(): Flow<Resource<PopularResponse>> {
        return flow {
            repo.getPopularMovies().collect { dataResponse ->
                emit(Resource.Success(dataResponse.data?.toDomain()))
            }
        }
    }
}
