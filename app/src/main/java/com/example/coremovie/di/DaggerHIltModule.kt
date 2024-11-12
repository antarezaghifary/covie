package com.example.coremovie.di

import android.content.Context
import com.example.coremovie.BuildConfig
import com.example.coremovie.data.datasource.HomeDataSource
import com.example.coremovie.data.remote.api.ApiService
import com.example.coremovie.data.repository.HomeRepository
import com.example.coremovie.domain.interactor.HomeInteractor
import com.example.coremovie.domain.usecase.HomeUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext appContext: Context): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client =

            OkHttpClient.Builder().apply {
                addInterceptor(
                    Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        return@Interceptor chain.proceed(builder.build())
                    }
                )

                addInterceptor(interceptor)
            }
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        // Create and return your ApiService instance (e.g., using Retrofit)
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideHomeDataSource(apiService: ApiService): HomeRepository {
        return HomeDataSource(apiService)
    }

    @Provides
    fun provideGetItemsUseCase(context: Context, repository: HomeRepository): HomeUsecase {
        return HomeInteractor(context, repository)
    }

    // Provide other dependencies
}