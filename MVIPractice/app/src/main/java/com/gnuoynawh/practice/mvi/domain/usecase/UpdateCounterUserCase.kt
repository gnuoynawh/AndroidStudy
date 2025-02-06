package com.gnuoynawh.practice.mvi.domain.usecase

import com.gnuoynawh.practice.mvi.data.repository.CounterRepository

class UpdateCounterUserCase(private val repository: CounterRepository) {
    suspend operator fun invoke(count: Int) {
        repository.updateCount(count)
    }
}