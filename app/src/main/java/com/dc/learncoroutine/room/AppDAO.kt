package com.dc.learncoroutine.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dc.learncoroutine.localcall.model.TodoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(todoModel: TodoModel) : Long

    @Delete
    fun deleteTodo(todoModel: TodoModel)

    @Query("SELECT * FROM todo")
     fun getAllTodos(): Flow<List<TodoModel>>
}