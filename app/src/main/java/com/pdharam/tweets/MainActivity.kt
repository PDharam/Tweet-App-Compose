@file:OptIn(ExperimentalMaterial3Api::class)

package com.pdharam.tweets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pdharam.tweets.api.TweetsyApi
import com.pdharam.tweets.screens.DetailsScreen
import com.pdharam.tweets.screens.categoryScreen
import com.pdharam.tweets.ui.theme.TweetsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    @Inject
    lateinit var tweetsyApi: TweetsyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TweetsTheme {
                Scaffold(topBar = {
                    TopAppBar(title = { Text(text = "Tweetsy") })
                }) {

                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "category") {
            composable(route = "category") {
                categoryScreen() {
                    navController.navigate("details/$it")
                }
            }

            composable(route = "details/{category}", arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )) {
                val category = it.arguments!!.getString("category")
                category?.let { it1 -> DetailsScreen(category = it1) }
            }


        }
    }
}
