package com.gnuoynawh.prj.mvvmexam.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gnuoynawh.prj.mvvmexam.data.local.db.dao.AudioDao
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio

@Database(
    entities = [Audio::class],
    version = 1,
    exportSchema = false
)
abstract class MyRoomDatabase: RoomDatabase() {

    abstract fun audioDao(): AudioDao

    companion object {
        const val DB_NAME = "MvvmExam.db"
    }
}