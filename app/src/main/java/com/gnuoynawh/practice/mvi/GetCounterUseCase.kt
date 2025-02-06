package com.gnuoynawh.practice.mvi

import kotlinx.coroutines.flow.Flow

class GetCounterUseCase(private val repository: CounterRepository) {
    operator fun invoke(): Flow<Int> {
        return repository.getCounter()
    }
}