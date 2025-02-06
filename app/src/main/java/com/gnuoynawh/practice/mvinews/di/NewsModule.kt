package com.gnuoynawh.practice.mvinews.di

import android.content.Context
import androidx.room.Room
import com.gnuoynawh.practice.mvinews.data.local.NewsDatabase
import com.gnuoynawh.practice.mvinews.data.local.dto.NewsDao
import com.gnuoynawh.practice.mvinews.data.remote.api.NewsApiService
import com.gnuoynawh.practice.mvinews.data.repository.NewsRepository
import com.gnuoynawh.practice.mvinews.domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    private const val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService =
        retrofit.create(NewsApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, "news_db").build()

    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao()

    @Provides
    fun provideRepository(apiService: NewsApiService, newsDao: NewsDao) =
        NewsRepository(apiService, newsDao)

    @Provides
    fun provideGetNewsUseCase(repository: NewsRepository) = GetNewsUseCase(repository)
}
