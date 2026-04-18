package com.namakamu.myprofileapp.viewmodel

import androidx.lifecycle.ViewModel
import com.namakamu.myprofileapp.data.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteViewModel : ViewModel() {
    // Menyimpan daftar catatan
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private var nextId = 1 // Auto-increment ID untuk catatan baru

    // 1. Tambah Catatan
    fun addNote(title: String, content: String) {
        if (title.isNotBlank() || content.isNotBlank()) {
            val newNote = Note(id = nextId++, title = title, content = content)
            _notes.update { it + newNote }
        }
    }

    // 2. Edit Catatan
    fun updateNote(id: Int, newTitle: String, newContent: String) {
        _notes.update { currentList ->
            currentList.map { note ->
                if (note.id == id) note.copy(title = newTitle, content = newContent) else note
            }
        }
    }

    // 3. Toggle Favorit (Bintang)
    fun toggleFavorite(id: Int) {
        _notes.update { currentList ->
            currentList.map { note ->
                if (note.id == id) note.copy(isFavorite = !note.isFavorite) else note
            }
        }
    }

    // 4. Ambil 1 Catatan berdasarkan ID
    fun getNoteById(id: Int): Note? {
        return _notes.value.find { it.id == id }
    }
}