package com.gnuoynawh.practice.mvi

/**
 * 사용자의 액션 - 증가, 감소, 리셋
 */
sealed class CounterIntent {
    object Increment: CounterIntent()
    object Decrement: CounterIntent()
    object Reset: CounterIntent()
}