package com.example.coremovie.domain.model.popular

data class ResultsItem(
	val overview: String,
	val originalLanguage: String,
	val originalTitle: String,
	val video: Boolean,
	val title: String,
	val genreIds: List<Int>,
	val posterPath: String,
	val backdropPath: String,
	val releaseDate: String,
	val popularity: Any,
	val voteAverage: Any,
	val id: Int,
	val adult: Boolean,
	val voteCount: Int
)
