package com.pdharam.tweets.api

import com.pdharam.tweets.model.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyApi {

    @GET("v3/b/6676d3ddad19ca34f87cf763?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<Tweet>>

    @GET("v3/b/6676d3ddad19ca34f87cf763?meta=false")
    @Headers("X-JSON-Path:\$..category")
    suspend fun getCategories(): Response<List<String>>
}