package com.gnuoynawh.practice.mvinews.data.local.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gnuoynawh.practice.mvinews.data.local.entities.NewsArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_article")
    fun getNewsArticles(): Flow<List<NewsArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<NewsArticle>)

    @Query("DELETE FROM news_article")
    suspend fun clearArticles()

}