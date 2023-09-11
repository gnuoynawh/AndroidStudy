package com.gnuoynawh.prj.mvvmexam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio
import com.gnuoynawh.prj.mvvmexam.data.repository.AudioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    application: Application
): AndroidViewModel(application)  {

    private val repository = AudioRepository(application)
    val items = repository.getList()

    fun addItem() = viewModelScope.launch {
        val fileName = "Title ${items.value!!.size + 1}"
        repository.addItem(Audio(0, fileName))
    }

    fun updateItem(audio: Audio) = viewModelScope.launch {
        val fileName = audio.fileName + " new!"
        repository.updateItem(Audio(audio.id, fileName))
    }

    fun deleteItem(audio: Audio) = viewModelScope.launch {
        repository.deleteItem(audio.id)
    }

}