package com.kcode.netwrok.api

sealed class ApiResult {
    class Success<T>(val data: T) : ApiResult()
    class Failure(val message: String) : ApiResult()
    class ErrorWithCode(val code: Int, val message: String) : ApiResult()
    class Exception(val message: String) : ApiResult()
    class Validation(val message: String) : ApiResult()
    class IsLoading(val isLoading: Boolean) : ApiResult()
    object NoInternet : ApiResult()
    object Error : ApiResult()
}
