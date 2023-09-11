package com.gnuoynawh.prj.exam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gnuoynawh.prj.exam.data.entity.File
import com.gnuoynawh.prj.exam.data.repository.FileRepository
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
): AndroidViewModel(application)  {

    private val repository = FileRepository(application)
    val items = repository.getList()

    fun addItem() = viewModelScope.launch {
        val fileName = "Title ${items.value!!.size + 1}"
        repository.addItem(File(0, fileName))
    }

    fun updateItem(file: File) = viewModelScope.launch {
        val fileName = file.fileName + " new!"
        repository.updateItem(File(file.id, fileName))
    }

    fun deleteItem(file: File) = viewModelScope.launch {
        repository.deleteItem(file.id)
    }

}