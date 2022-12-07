package com.example.coding_challenge.data.network

import retrofit2.HttpException
import retrofit2.Response

/**
 * this function is created to act as a response wrapper so not required to write code for checking api response for each api call
 * instead we can use this function as a wrapper, this is supported from kotlin 1.5
 */
suspend fun <T : Any> handleApi(
    /**
     * this is the actual api call passed as suspend function
     */
    execute: suspend () -> Response<T>
): ApiResult<T> {
    /**
     * added try catch to handle the exception in api calls, for example when no internet so it will throw UnknownHostException
     */
    return try {
        /**
         * executing the api
         */
        val response = execute()

        /**
         * fetching the body from received response
         */
        val body = response.body()
        /**
         * checking if response is successful
         */
        if (response.isSuccessful && body != null) {
            ApiSuccess(body)
        }
        /**
         * if api response is not successful so returning the ApiError with response code and message
         */
        else {
            ApiError(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ApiError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiException(e)
    }
}