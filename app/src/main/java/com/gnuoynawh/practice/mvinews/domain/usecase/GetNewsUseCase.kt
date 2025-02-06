package com.gnuoynawh.practice.mvinews.domain.usecase

import com.gnuoynawh.practice.mvinews.data.local.entities.NewsArticle
import com.gnuoynawh.practice.mvinews.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<NewsArticle>> {
        return repository.getNews()
    }
}