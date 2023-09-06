package com.gnuoynawh.prj.mvvmexam.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio
import com.gnuoynawh.prj.mvvmexam.data.repository.AudioRepository
import com.gnuoynawh.prj.mvvmexam.domain.audio.AddAudioItemUseCase
import com.gnuoynawh.prj.mvvmexam.domain.audio.DeleteAudioItemUseCase
import com.gnuoynawh.prj.mvvmexam.domain.audio.GetAudioItemUseCase
import com.gnuoynawh.prj.mvvmexam.domain.audio.GetAudioListUseCase
import com.gnuoynawh.prj.mvvmexam.domain.audio.UpdateAudioItemUseCase
import com.gnuoynawh.prj.mvvmexam.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val addAudioItemUseCase: AddAudioItemUseCase,
    private val deleteAudioItemUseCase: DeleteAudioItemUseCase,
    private val getAudioItemUseCase: GetAudioItemUseCase,
    private val getAudioListUseCase: GetAudioListUseCase,
    private val updateAudioItemUseCase: UpdateAudioItemUseCase
): BaseViewModel()  {

    private var _audioListLiveData = MutableLiveData<List<Audio>>()
    var audioListLiveData: LiveData<List<Audio>> = _audioListLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        val list = getAudioListUseCase()
        _audioListLiveData.postValue(list)
    }

    fun refresh() = viewModelScope.launch {
        val list = getAudioListUseCase()
        _audioListLiveData.postValue(list)
    }

    fun addItem() = viewModelScope.launch {
        val fileName = "Title ${audioListLiveData.value!!.size + 1}"
        val audio = Audio(0, fileName)
        addAudioItemUseCase(audio)
        refresh()
    }

    fun updateItem(audio: Audio) = viewModelScope.launch {
        val fileName = audio.fileName + " new!"
        val modify = Audio(audio.id, fileName)
        updateAudioItemUseCase(modify)
        refresh()
    }

    fun deleteItem(audio: Audio) = viewModelScope.launch {
        deleteAudioItemUseCase(audio.id)
        refresh()
    }
}