package com.gnuoynawh.practice.mvinews.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.gnuoynawh.practice.mvinews.presentation.ui.theme.MVINewsPracticeTheme
import com.gnuoynawh.practice.mvinews.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVINewsPracticeTheme {

                val state by viewModel.state.collectAsState()
                Log.e("TEST", "list : " + state.newsList.size)

                LazyColumn(
                    contentPadding =  PaddingValues(all = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items = state.newsList) { article ->
                        Text(text = "타이틀 : ${article.title}")
                    }
                }
            }
        }
    }
}