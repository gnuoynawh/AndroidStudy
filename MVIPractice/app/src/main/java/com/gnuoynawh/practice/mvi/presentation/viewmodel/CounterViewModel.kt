package com.gnuoynawh.practice.mvi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnuoynawh.practice.mvi.CounterIntent
import com.gnuoynawh.practice.mvi.domain.usecase.GetCounterUseCase
import com.gnuoynawh.practice.mvi.domain.usecase.UpdateCounterUserCase
import com.gnuoynawh.practice.mvi.presentation.state.CounterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    //private val repository: CounterRepository

    private val getCounterUseCase: GetCounterUseCase,
    private val updateCounterUserCase: UpdateCounterUserCase
) : ViewModel() {

    private val _state = MutableStateFlow(CounterState())           // MutableStateFlow 는 현재 상태 저장
    val state : StateFlow<CounterState> = _state.asStateFlow()      // StateFlow 를 사용해 UI가 구독할 수 있도록 제공

    init {
        loadCounter()
    }

    private fun loadCounter() {
        viewModelScope.launch {
//            repository.getCounter()
//                .collect { count ->
//                    _state.value = CounterState(count = count)
//                }
            getCounterUseCase().collect { count ->
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
                    //repository.updateCount(newCount)
                    updateCounterUserCase.invoke(newCount)
                    _state.value = _state.value.copy(count = newCount)
                }
                is CounterIntent.Decrement -> {
                    val newCount = _state.value.count - 1
                    //repository.updateCount(newCount)
                    updateCounterUserCase.invoke(newCount)
                    _state.value = _state.value.copy(count = newCount)
                }
                is CounterIntent.Reset -> {
                    //repository.updateCount(0)
                    updateCounterUserCase.invoke(0)
                    _state.value = _state.value.copy(count = 0)
                }
            }
        }
    }
}