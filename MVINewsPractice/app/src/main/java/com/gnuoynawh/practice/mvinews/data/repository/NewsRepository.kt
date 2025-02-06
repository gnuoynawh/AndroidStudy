package com.gnuoynawh.practice.mvinews.data.repository

import com.gnuoynawh.practice.mvinews.data.local.dto.NewsDao
import com.gnuoynawh.practice.mvinews.data.local.entities.NewsArticle
import com.gnuoynawh.practice.mvinews.data.remote.api.NewsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: NewsApiService,
    private val newsDao: NewsDao
) {
    fun getNews(): Flow<List<NewsArticle>> = flow {
        val cachedNews = newsDao.getNewsArticles().first()
        if (cachedNews.isNotEmpty()) {
            emit(cachedNews)
        }

        try {
            val response = apiService.getTopHeadlines()
            val articles = response.articles.map { it.toEntity() }

            newsDao.clearArticles()
            newsDao.insertArticles(articles)
            emit(articles)

        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}