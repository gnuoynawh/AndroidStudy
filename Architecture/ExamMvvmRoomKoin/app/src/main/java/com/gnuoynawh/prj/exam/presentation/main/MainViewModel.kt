package com.gnuoynawh.prj.exam.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gnuoynawh.prj.exam.data.entity.File
import com.gnuoynawh.prj.exam.domain.file.AddFileItemUseCase
import com.gnuoynawh.prj.exam.domain.file.DeleteFileItemUseCase
import com.gnuoynawh.prj.exam.domain.file.GetFileItemUseCase
import com.gnuoynawh.prj.exam.domain.file.GetFileListUseCase
import com.gnuoynawh.prj.exam.domain.file.UpdateFileItemUseCase
import com.gnuoynawh.prj.exam.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    private val addFileItemUseCase: AddFileItemUseCase,
    private val deleteFileItemUseCase: DeleteFileItemUseCase,
    private val getFileItemUseCase: GetFileItemUseCase,
    private val getFileListUseCase: GetFileListUseCase,
    private val updateFileItemUseCase: UpdateFileItemUseCase
): BaseViewModel()  {

    private var _fileListLiveData = MutableLiveData<List<File>>()
    var fileListLiveData: LiveData<List<File>> = _fileListLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        val list = getFileListUseCase()
        _fileListLiveData.postValue(list)
    }

    fun refresh() = viewModelScope.launch {
        val list = getFileListUseCase()
        _fileListLiveData.postValue(list)
    }

    fun addItem() = viewModelScope.launch {
        val fileName = "Title ${fileListLiveData.value!!.size + 1}"
        val file = File(0, fileName)
        addFileItemUseCase(file)
        refresh()
    }

    fun updateItem(file: File) = viewModelScope.launch {
        val fileName = file.fileName + " new!"
        val modify = File(file.id, fileName)
        updateFileItemUseCase(modify)
        refresh()
    }

    fun deleteItem(file: File) = viewModelScope.launch {
        deleteFileItemUseCase(file.id)
        refresh()
    }
}