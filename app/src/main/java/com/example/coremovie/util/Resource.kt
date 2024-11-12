package com.example.coremovie.util

sealed class Resource<T>(
    val data: T? = null,
    val error: ERROR? = null,
    val code: Int = 0,
    val errMsg: String? = null
) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(error: ERROR, errMsg: String? = null, code: Int = 0, data: T? = null) :
        Resource<T>(data, error, code, errMsg)
}

