package com.gnuoynawh.prj.mvvmexam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gnuoynawh.prj.mvvmexam.data.dao.AudioDao
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio

@Database(
    entities = [Audio::class],
    version = 1,
    exportSchema = false
)
abstract class MyRoomDatabase: RoomDatabase() {

    abstract fun audioDao(): AudioDao

    companion object {
        private var instance: MyRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MyRoomDatabase? {
            if (instance == null) {
                synchronized(MyRoomDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyRoomDatabase::class.java,
                        "MvvmExam.db"
                    )
                    .allowMainThreadQueries()
                    .build()
                }
            }
            return instance
        }
    }
}