package com.example.coremovie.domain.model.popular

data class PopularResponse(
	val page: Int,
	val totalPages: Int,
	val results: List<ResultsItem>,
	val totalResults: Int
)

