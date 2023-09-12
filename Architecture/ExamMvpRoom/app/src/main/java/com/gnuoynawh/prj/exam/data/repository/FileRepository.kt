package com.gnuoynawh.prj.exam.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gnuoynawh.prj.exam.data.MyDatabaseProvider
import com.gnuoynawh.prj.exam.data.MyRoomDatabase
import com.gnuoynawh.prj.exam.data.dao.FileDao
import com.gnuoynawh.prj.exam.data.entity.File

class FileRepository(
    application: Application
) {
    private val fileDao: FileDao
    private val fileList: LiveData<List<File>>

    init {
        fileDao = MyDatabaseProvider.provideDB(application).audioDao()
        fileList = fileDao.getAll()
    }

    fun getList(): LiveData<List<File>> {
        return fileList
    }

    fun addItem(file: File) {
        fileDao.insert(file)
    }

    fun updateItem(file: File) {
        fileDao.update(file)
    }

    fun deleteItem(id: Long) {
        fileDao.delete(id)
    }

}