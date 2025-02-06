package com.gnuoynawh.practice.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CounterRepository(private val counterDao: CounterDao) {

    fun getCounter() : Flow<Int> = flow {
        val counter = counterDao.getCounter() ?: CounterEntity(count = 0)
        emit(counter.count)
    }

    suspend fun updateCount(count: Int) {
        val entity = CounterEntity(id = 1, count = count)
        counterDao.insertCounter(entity)
    }
}