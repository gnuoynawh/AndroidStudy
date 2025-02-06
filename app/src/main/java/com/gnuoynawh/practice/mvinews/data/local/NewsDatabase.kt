package com.gnuoynawh.practice.mvinews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gnuoynawh.practice.mvinews.data.local.dto.NewsDao
import com.gnuoynawh.practice.mvinews.data.local.entities.NewsArticle

@Database(entities = [NewsArticle::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao() : NewsDao
}