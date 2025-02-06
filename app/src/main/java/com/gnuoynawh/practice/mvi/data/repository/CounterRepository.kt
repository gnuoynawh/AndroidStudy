package com.gnuoynawh.practice.mvi.data.repository

import com.gnuoynawh.practice.mvi.data.local.CounterDao
import com.gnuoynawh.practice.mvi.data.local.CounterEntity
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