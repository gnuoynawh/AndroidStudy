package com.gnuoynawh.practice.mvi

class UpdateCounterUserCase(private val repository: CounterRepository) {
    suspend operator fun invoke(count: Int) {
        repository.updateCount(count)
    }
}