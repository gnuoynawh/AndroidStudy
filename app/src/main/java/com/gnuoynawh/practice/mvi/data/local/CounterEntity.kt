package com.gnuoynawh.practice.mvi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter_table")
data class CounterEntity(
    @PrimaryKey
    val id: Int = 1,    // 1로 고정해서 단일 행 데이터만 저장하도록 설정
    val count: Int
)