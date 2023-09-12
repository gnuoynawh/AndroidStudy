package com.gnuoynawh.prj.exam

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPresenter(
    view: MainContract.View
): MainContract.Presenter {

    private var fileItem: MutableList<File> = mutableListOf()
    private val _fileList = MutableLiveData<MutableList<File>>()

    val fileList: LiveData<MutableList<File>>
        get() = _fileList

    override fun addItem() {
        CoroutineScope(Dispatchers.Main).launch {
            var index = fileList.value?.size ?: 0
            index += 1
            fileItem.add(File(index.toLong(), "Title $index"))

            _fileList.value = fileItem
        }
    }

}