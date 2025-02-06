package com.gnuoynawh.practice.mvi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gnuoynawh.practice.mvi.data.repository.CounterRepository
import com.gnuoynawh.practice.mvi.domain.usecase.GetCounterUseCase
import com.gnuoynawh.practice.mvi.domain.usecase.UpdateCounterUserCase

class CounterViewModelFactory(private val repository: CounterRepository) : ViewModelProvider.Factory {

//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
//            return CounterViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            return CounterViewModel(
                getCounterUseCase = GetCounterUseCase(repository),
                updateCounterUserCase = UpdateCounterUserCase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}