package com.gnuoynawh.practice.mvi.presentation.state

/**
 * 앱의 현재 상태
 */
data class CounterState(
    val count: Int = 0,             // 현재 카운트 값
    val isLoading: Boolean = false  // 로딩 여부 추가해서 비동기 작업으로 확장
)