package com.gnuoynawh.prj.exam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gnuoynawh.prj.exam.data.dao.FileDao
import com.gnuoynawh.prj.exam.data.entity.File

@Database(
    entities = [File::class],
    version = 1,
    exportSchema = false
)
abstract class MyRoomDatabase: RoomDatabase() {

    abstract fun audioDao(): FileDao

    companion object {
        private var instance: MyRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MyRoomDatabase? {
            if (instance == null) {
                synchronized(MyRoomDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyRoomDatabase::class.java,
                        "ExamMvvmRoom.db"
                    )
                    .allowMainThreadQueries()
                    .build()
                }
            }
            return instance
        }
    }
}