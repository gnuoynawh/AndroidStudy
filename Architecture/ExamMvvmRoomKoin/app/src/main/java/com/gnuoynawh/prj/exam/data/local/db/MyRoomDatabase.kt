package com.gnuoynawh.prj.exam.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gnuoynawh.prj.exam.data.local.db.dao.FileDao
import com.gnuoynawh.prj.exam.data.entity.File

@Database(
    entities = [File::class],
    version = 1,
    exportSchema = false
)
abstract class MyRoomDatabase: RoomDatabase() {

    abstract fun audioDao(): FileDao

    companion object {
        const val DB_NAME = "ExamMvvmRoomKoin.db"
    }
}