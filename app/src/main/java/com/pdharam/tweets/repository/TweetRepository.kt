package com.pdharam.tweets.repository

import com.pdharam.tweets.api.TweetsyApi
import com.pdharam.tweets.model.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsyApi: TweetsyApi) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories
    private val _tweets = MutableStateFlow<List<Tweet>>(emptyList())
    val tweets: StateFlow<List<Tweet>>
        get() = _tweets

    suspend fun getCategories() {
        val result = tweetsyApi.getCategories()
        if (result.isSuccessful) {
            _categories.emit(result.body()!!)
        }
    }

    suspend fun getTweets(category: String) {
        val result = tweetsyApi.getTweets("\$[?(@.category==\"$category\")]")
        if (result.isSuccessful) {
            _tweets.emit(result.body()!!)
        }
    }

}