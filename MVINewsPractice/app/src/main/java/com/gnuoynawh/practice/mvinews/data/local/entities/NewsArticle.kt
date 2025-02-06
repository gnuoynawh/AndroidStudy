package com.gnuoynawh.practice.mvinews.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_article")
data class NewsArticle(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,
    val url: String,
    val imageUrl: String?
)