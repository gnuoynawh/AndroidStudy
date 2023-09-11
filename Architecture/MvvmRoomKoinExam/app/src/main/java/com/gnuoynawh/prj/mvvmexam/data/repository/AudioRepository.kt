package com.gnuoynawh.prj.mvvmexam.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gnuoynawh.prj.mvvmexam.data.local.db.MyRoomDatabase
import com.gnuoynawh.prj.mvvmexam.data.local.db.dao.AudioDao
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AudioRepository(
    private val audioDao: AudioDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getList(): List<Audio> = withContext(ioDispatcher) {
        audioDao.getAll()
    }

    suspend fun getItem(id: Long): Audio = withContext(ioDispatcher) {
        audioDao.getById(id)
    }

    suspend fun addItem(audio: Audio) = withContext(ioDispatcher) {
        audioDao.insert(audio)
    }

    suspend fun updateItem(audio: Audio) = withContext(ioDispatcher) {
        audioDao.update(audio)
    }

    suspend fun deleteItem(id: Long) = withContext(ioDispatcher) {
        audioDao.delete(id)
    }

}