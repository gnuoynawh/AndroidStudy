package com.gnuoynawh.prj.exam.data

import android.app.Application
import androidx.room.Room


object MyDatabaseProvider {

    private const val DB_NAME = "ExamMvpRoom.db"

    fun provideDB(application: Application) = Room.databaseBuilder(
        application,
        MyRoomDatabase::class.java,
        DB_NAME
    ).build()
}