package com.example.andriiginting.news.network.Error

class ApiError {
    private val status: String? = null
    private val code: String? = null
    private val message: String?=null

    fun status(): String?{
        return status
    }

    fun messageResponse(): String?{
        return message
    }
}