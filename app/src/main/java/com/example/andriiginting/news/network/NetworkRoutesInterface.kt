package com.example.andriiginting.news.network

import com.example.andriiginting.news.model.sources.SourceResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkRoutesInterface {

    @GET("v2/sources")
    fun getAllSources(@Query("language") language: String,
                      @Query("country") country: String)
            : Observable<Response<SourceResponse>>

}