package com.pdharam.tweets.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pdharam.tweets.model.Tweet
import com.pdharam.tweets.viewmodel.TweetViewModel

@Composable
fun DetailsScreen(modifier: Modifier = Modifier, category: String) {
    val tweetViewModel: TweetViewModel = hiltViewModel()
    val tweets: State<List<Tweet>> = tweetViewModel.tweets.collectAsState()
    LazyColumn {
        items(tweets.value) {
            TweetItem(modifier = modifier, tweet = it.tweet)
        }
    }

}

@Composable
fun TweetItem(modifier: Modifier, tweet: String) {
    return Card(modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        content = {
            Text(
                text = tweet,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(16.dp)
            )
        })
}