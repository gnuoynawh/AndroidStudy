package com.gnuoynawh.prj.mvvmexam.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gnuoynawh.prj.mvvmexam.data.MyRoomDatabase
import com.gnuoynawh.prj.mvvmexam.data.dao.AudioDao
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio

class AudioRepository(
    application: Application
) {
    private val audioDao: AudioDao
    private val audioList: LiveData<List<Audio>>

    init {
        val db = MyRoomDatabase.getInstance(application)
        audioDao = db!!.audioDao()
        audioList = audioDao.getAll()
    }

    fun getList(): LiveData<List<Audio>> {
        return audioList
    }

    fun addItem(audio: Audio) {
        audioDao.insert(audio)
    }

    fun updateItem(audio: Audio) {
        audioDao.update(audio)
    }

    fun deleteItem(id: Long) {
        audioDao.delete(id)
    }

}