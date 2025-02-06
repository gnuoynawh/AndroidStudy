package com.gnuoynawh.practice.mvi.domain.usecase

import com.gnuoynawh.practice.mvi.data.repository.CounterRepository
import kotlinx.coroutines.flow.Flow

class GetCounterUseCase(private val repository: CounterRepository) {
    operator fun invoke(): Flow<Int> {
        return repository.getCounter()
    }
}