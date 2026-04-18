package com.namakamu.myprofileapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoViewModel : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos = _todos.asStateFlow()

    fun addTodo(text: String) {
        if (text.isBlank()) return
        val newTodo = Todo(id = _todos.value.size + 1, text = text)
        _todos.update { it + newTodo }
    }
}