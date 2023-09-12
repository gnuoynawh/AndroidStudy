package com.gnuoynawh.prj.exam.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gnuoynawh.prj.exam.data.entity.File

@Dao
interface FileDao {

    @Query("SELECT * FROM File")
    fun getAll(): LiveData<List<File>>

    @Query("SELECT * FROM File WHERE id = :id")
    fun getById(id: Long): File

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(file: File)

    @Update
    fun update(file: File)

    @Query("DELETE FROM File WHERE id=:id")
    fun delete(id: Long)

}