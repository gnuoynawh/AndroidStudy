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

}