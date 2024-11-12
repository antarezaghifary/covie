package com.example.coremovie.data.remote.api

import com.example.coremovie.data.model.popular.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getDataPopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<PopularResponse>
}

/**
 * @GET("discover/movie")
 *     suspend fun getMovie(
 *         @Query("page") page: Int,
 *         @Query("api_key") api_key: String,
 *         @Query("with_genres") with_genres: Int,
 *         @Header("language") language: String
 *     ): MovieResponse
 *
 *     @GET("movie/{movie_id}/reviews")
 *     suspend fun getReview(
 *         @Path("movie_id") movie_id: Int,
 *         @Query("api_key") api_key: String,
 *         @Header("language") language: String
 *     ): ReviewResponse
 *
 *     @GET("movie/{movie_id}/reviews")
 *     suspend fun getReviewAll(
 *         @Path("movie_id") movie_id: Int,
 *         @Query("page") page: Int,
 *         @Query("api_key") api_key: String,
 *         @Header("language") language: String
 *     ): ReviewResponse
 *
 *     @GET("movie/{movie_id}/videos")
 *     suspend fun getTrailer(
 *         @Path("movie_id") movie_id: Int,
 *         @Query("api_key") api_key: String
 *     ): TrailerResponse
 *
 *     @GET("movie/upcoming")
 *     suspend fun getUpcoming(
 *         @Query("api_key") api_key: String,
 *         @Query("language") language: String
 *     ): UpcomingResponse
 *
 *     @GET("movie/popular")
 *     suspend fun getPopular(
 *         @Query("api_key") api_key: String,
 *         @Query("language") language: String
 *     ): PopularResponse
 *
 *     @GET("movie/top_rated")
 *     suspend fun getTop(
 *         @Query("api_key") api_key: String,
 *         @Query("language") language: String
 *     ): TopRatedResponse
 */