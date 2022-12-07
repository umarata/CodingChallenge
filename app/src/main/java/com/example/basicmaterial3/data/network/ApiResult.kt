package com.example.basicmaterial3.data.network

/**
 * Api result sealed interface created to handle all three states
 * 1. Success response by ApiSuccess subclass
 * 2. Error response by ApiError subclass
 * 3. Exception by ApiException subclass
 */
sealed interface ApiResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
class ApiError<T : Any>(val code: Int, val message: String?) : ApiResult<T>
class ApiException<T : Any>(val e: Throwable) : ApiResult<T>