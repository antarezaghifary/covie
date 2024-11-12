package com.example.coremovie.domain.model.popular

import com.example.coremovie.data.model.popular.PopularResponse as DataPopularResponse
import com.example.coremovie.data.model.popular.ResultsItem as DataResultsItem

fun DataPopularResponse.toDomain(): PopularResponse {
    return PopularResponse(
        page = page ?: 0,
        results = results!!.map { it!!.toDataDomain() },
        totalPages = totalPages ?: 0,
        totalResults = totalResults ?: 0
    )
}
fun DataResultsItem.toDataDomain(): ResultsItem {
    return ResultsItem(
        id = id ?: 0,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        video = video ?: false,
        genreIds = genreIds?.filterNotNull() ?: emptyList(),
        posterPath = posterPath.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        popularity = popularity ?: "",
        voteAverage = voteAverage ?: "",
        adult = adult ?: false,
        voteCount = voteCount ?: 0
    )
}
