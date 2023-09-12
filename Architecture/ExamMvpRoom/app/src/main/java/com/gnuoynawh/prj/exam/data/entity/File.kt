package com.gnuoynawh.prj.exam.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *  데이터 클래스 추가
 */
@Entity
data class File(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val fileName: String
)
