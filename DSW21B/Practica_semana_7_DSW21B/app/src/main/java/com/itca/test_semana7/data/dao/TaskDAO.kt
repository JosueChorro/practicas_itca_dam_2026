package com.itca.test_semana7.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.itca.test_semana7.data.entity.Task
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDAO {

    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAll(): Flow<List<Task>>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}