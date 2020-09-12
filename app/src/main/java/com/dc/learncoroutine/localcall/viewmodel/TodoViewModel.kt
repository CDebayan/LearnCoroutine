package com.dc.learncoroutine.localcall.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dc.learncoroutine.localcall.model.TodoModel
import com.dc.learncoroutine.localcall.repository.TodoRepository
import kotlinx.coroutines.Dispatchers

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository: TodoRepository = TodoRepository.getInstance(getApplication())
    val todoList: LiveData<List<TodoModel>> get() = todoRepository.getTodoList()

//    fun fetchTodoList() = liveData(Dispatchers.IO) {
//        emit(TodoListState.Loading)
//        emit(todoRepository.addAllTodo())
//    }
//
//    fun deleteTodo(todoModel: TodoModel) = liveData(Dispatchers.IO) {
//        emit(TodoListState.Loading)
//        emit(todoRepository.deleteTodo(todoModel))
//    }

    fun addTodo(
        title: String,
        description: String
    ) = liveData(Dispatchers.IO) {

        emit(TodoState.Loading)

        emit(
            todoRepository.addTodo(
                title = title,
                description = description
            )
        )


    }

    sealed class TodoState {
        object Loading : TodoState()
        data class Success(val message: String?) : TodoState()
        data class Error(val status: Int, val message: String) : TodoState()
    }
}

