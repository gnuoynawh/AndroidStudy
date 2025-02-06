package com.gnuoynawh.practice.mvinews.data.remote.models

import com.gnuoynawh.practice.mvinews.data.local.entities.NewsArticle

data class NewsArticleDto(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?
) {
    fun toEntity(): NewsArticle {
        return NewsArticle(
            title = title,
            description = description,
            url = url,
            imageUrl = urlToImage
        )
    }
}