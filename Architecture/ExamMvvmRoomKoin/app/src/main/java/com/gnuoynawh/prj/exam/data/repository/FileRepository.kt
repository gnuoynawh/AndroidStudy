package com.gnuoynawh.prj.exam.data.repository

import com.gnuoynawh.prj.exam.data.local.db.dao.FileDao
import com.gnuoynawh.prj.exam.data.entity.File
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FileRepository(
    private val fileDao: FileDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getList(): List<File> = withContext(ioDispatcher) {
        fileDao.getAll()
    }

    suspend fun getItem(id: Long): File = withContext(ioDispatcher) {
        fileDao.getById(id)
    }

    suspend fun addItem(file: File) = withContext(ioDispatcher) {
        fileDao.insert(file)
    }

    suspend fun updateItem(file: File) = withContext(ioDispatcher) {
        fileDao.update(file)
    }

    suspend fun deleteItem(id: Long) = withContext(ioDispatcher) {
        fileDao.delete(id)
    }

}