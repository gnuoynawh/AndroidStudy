package com.gnuoynawh.practice.mvinews.presentation.state

import com.gnuoynawh.practice.mvinews.data.local.entities.NewsArticle

data class NewsState(
    val newsList: List<NewsArticle> = emptyList(),
    val error: String? = null
)