package com.gnuoynawh.prj.exam

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.gnuoynawh.prj.exam.data.entity.File
import com.gnuoynawh.prj.exam.data.repository.FileRepository
import kotlinx.coroutines.withContext

class MainPresenter(
    application: Application
): MainContract.Presenter {

    private val repository = FileRepository(application)
    val items = repository.getList()

    override fun addItem() {
        CoroutineScope(Dispatchers.IO).launch {
            val fileName = "Title ${items.value!!.size + 1}"
            repository.addItem(File(0, fileName))
        }
    }

    override fun updateItem(file: File) {
        CoroutineScope(Dispatchers.IO).launch {
            val fileName = file.fileName + " new!"
            repository.updateItem(File(file.id, fileName))
        }
    }

    override fun deleteItem(file: File) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteItem(file.id)
        }
    }

}