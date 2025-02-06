package com.gnuoynawh.practice.mvi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CounterDao {

    @Query("SELECT * FROM counter_table WHERE id = 1")
    suspend fun getCounter(): CounterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCounter(counter: CounterEntity)

    @Update
    suspend fun updateCounter(counter: CounterEntity)
}