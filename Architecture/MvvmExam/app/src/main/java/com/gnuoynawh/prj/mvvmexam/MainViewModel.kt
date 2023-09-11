package com.gnuoynawh.prj.mvvmexam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    // LiveData
    private var audioItem: MutableList<Audio> = mutableListOf()
    private val _audioList = MutableLiveData<MutableList<Audio>>()

    // Data!!
    val audioList: LiveData<MutableList<Audio>>
        get() = _audioList

    // 데이터 추가 이벤트
    fun addItem() {
        viewModelScope.launch {
            val index = audioItem.size + 1
            audioItem.add(Audio(index.toLong(), "Title $index"))
            _audioList.value = audioItem
        }
    }
}