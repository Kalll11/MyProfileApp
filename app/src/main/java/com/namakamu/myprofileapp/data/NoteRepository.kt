package com.namakamu.myprofileapp.data

import android.content.Context
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.namakamu.myprofileapp.db.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import com.namakamu.myprofileapp.db.db.Note

class NoteRepository(context: Context) {
    private val driver = AndroidSqliteDriver(NotesDatabase.Schema, context, "notes.db")
    private val database = NotesDatabase(driver)
    private val queries = database.noteQueries

    fun getAllNotes(isDescending: Boolean = true): Flow<List<Note>> {
        return if (isDescending) queries.selectAll().asFlow().mapToList(Dispatchers.IO)
        else queries.selectAllAsc().asFlow().mapToList(Dispatchers.IO)
    }

    fun searchNotes(query: String): Flow<List<Note>> {
        return queries.search(query, query).asFlow().mapToList(Dispatchers.IO)
    }

    suspend fun insertNote(title: String, content: String, isFavorite: Boolean = false) = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        // Gunakan pemanggilan eksplisit dengan nama variabelnya (Named Arguments)
        queries.insert(
            title = title,
            content = content,
            isFavorite = isFavorite,
            created_at = now
        )
    }

    suspend fun updateNote(id: Long, title: String, content: String, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        // Gunakan pemanggilan eksplisit dengan nama variabelnya
        queries.update(
            title = title,
            content = content,
            isFavorite = isFavorite,
            id = id
        )
    }

    suspend fun deleteNote(id: Long) = withContext(Dispatchers.IO) {
        queries.delete(id)
    }
}