package com.gnuoynawh.practice.mvi

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CounterViewModel(private val repository: CounterRepository) : ViewModel() {

    private val _state = MutableStateFlow(CounterState())           // MutableStateFlow 는 현재 상태 저장
    val state : StateFlow<CounterState> = _state.asStateFlow()      // StateFlow 를 사용해 UI가 구독할 수 있도록 제공

    init {
        loadCounter()
    }

    private fun loadCounter() {
        viewModelScope.launch {
            repository.getCounter()
                .collect { count ->
                    _state.value = CounterState(count = count)
                }
        }
    }

    // Intent 를 처리하는 함수
    fun processIntent(intent: CounterIntent) {
        viewModelScope.launch {
            when(intent) {
                is CounterIntent.Increment -> {
                    val newCount = _state.value.count + 1
                    repository.updateCount(newCount)
                    _state.value = _state.value.copy(count = newCount)
                }
                is CounterIntent.Decrement -> {
                    val newCount = _state.value.count - 1
                    repository.updateCount(newCount)
                    _state.value = _state.value.copy(count = newCount)
                }
                is CounterIntent.Reset -> {
                    repository.updateCount(0)
                    _state.value = _state.value.copy(count = 0)
                }
            }
        }
    }
}