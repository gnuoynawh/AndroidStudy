package com.gnuoynawh.prj.exam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    // LiveData
    private var fileItem: MutableList<File> = mutableListOf()
    private val _fileList = MutableLiveData<MutableList<File>>()

    // Data!!
    val fileList: LiveData<MutableList<File>>
        get() = _fileList

    // 데이터 추가 이벤트
    fun addItem() {
        viewModelScope.launch {
            val index = fileItem.size + 1
            fileItem.add(File(index.toLong(), "Title $index"))
            _fileList.value = fileItem
        }
    }
}