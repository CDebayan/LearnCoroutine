package com.dc.learncoroutine.localcall.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.dc.learncoroutine.localcall.model.TodoModel
import com.dc.learncoroutine.localcall.viewmodel.TodoViewModel
import com.dc.learncoroutine.room.AppDAO
import com.dc.learncoroutine.room.AppDatabase

class TodoRepository {
    companion object {
        private lateinit var instance: TodoRepository
        private lateinit var roomService: AppDAO

        fun getInstance(
            context: Context,
        ): TodoRepository {
            roomService = AppDatabase.invoke(context.applicationContext).appDAO()
            if (!::instance.isInitialized) {
                instance = TodoRepository()
            }
            return instance
        }
    }

    suspend fun addTodo(
        title: String,
        description: String,
    ): TodoViewModel.TodoState {
        val addTodoLocal = roomService.addTodo(TodoModel(title = title,description = description))
        if (addTodoLocal > 0) {
            return TodoViewModel.TodoState.Success("Todo added")
        }
        return TodoViewModel.TodoState.Error(-1,"Failed")
    }


    fun getTodoList(): LiveData<List<TodoModel>> {
        return roomService.getAllTodos().asLiveData()
    }

}


