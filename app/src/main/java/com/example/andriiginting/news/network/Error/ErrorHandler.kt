package com.example.andriiginting.news.network.Error

import com.example.andriiginting.news.network.NetworkClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class ErrorHandler {
    companion object {
        fun parseError(response: Response<*>): ApiError {
            val error: ApiError

            val converter: Converter<ResponseBody, ApiError>? =
                    NetworkClient().getRetrofitClient()
                            .responseBodyConverter(ApiError::class.java, arrayOfNulls<kotlin.Annotation>(0))

            try {
                error = converter?.convert(response.errorBody())!!
            } catch (e: IOException) {
                return ApiError()
            }
            return error
        }
    }
}