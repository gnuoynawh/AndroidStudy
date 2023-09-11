package com.gnuoynawh.prj.mvvmexam.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio

@Dao
interface AudioDao {

    @Query("SELECT * FROM Audio")
    fun getAll(): LiveData<List<Audio>>

    @Query("SELECT * FROM Audio WHERE id = :id")
    fun getById(id: Long): Audio

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(audio: Audio)

    @Update
    fun update(audio: Audio)

    @Query("DELETE FROM Audio WHERE id=:id")
    fun delete(id: Long)

}