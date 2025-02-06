package com.gnuoynawh.practice.mvinews.data.remote.api

import com.gnuoynawh.practice.mvinews.data.remote.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "33c6d20e6a4e44c3b2f4f0658a9ed59a"

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}