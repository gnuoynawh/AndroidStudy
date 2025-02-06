package com.gnuoynawh.practice.mvinews.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnuoynawh.practice.mvinews.domain.usecase.GetNewsUseCase
import com.gnuoynawh.practice.mvinews.presentation.state.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            getNewsUseCase.invoke()
                .catch { _state.value = NewsState(error = it.message) }
                .collect { articles ->

                    Log.e("TEST", "api result = " + articles.size)

                    _state.value = NewsState(newsList = articles)
                }
        }
    }
}
