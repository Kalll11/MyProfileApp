package com.namakamu.myprofileapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namakamu.myprofileapp.data.NoteRepository
import com.namakamu.myprofileapp.settings.SettingsManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.namakamu.myprofileapp.db.db.Note

// UI States sesuai modul: Loading, Empty, Content
sealed class UiState {
    object Loading : UiState()
    object Empty : UiState()
    data class Content(val notes: List<Note>) : UiState()
}

class NotesViewModel(
    private val repository: NoteRepository,
    val settingsManager: SettingsManager
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<UiState> = combine(
        settingsManager.sortOrderFlow,
        _searchQuery
    ) { sortOrder, query -> Pair(sortOrder, query) }
        .flatMapLatest { (sortOrder, query) ->
            val isDesc = sortOrder == "Newest"
            if (query.isBlank()) repository.getAllNotes(isDesc)
            else repository.searchNotes(query)
        }
        .map { notes ->
            if (notes.isEmpty()) UiState.Empty else UiState.Content(notes)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    // Tambahkan isFavorite dengan nilai default false
    fun addNote(title: String, content: String, isFavorite: Boolean = false) = viewModelScope.launch {
        repository.insertNote(title, content, isFavorite)
    }

    // Tambahkan parameter isFavorite dan teruskan ke repository
    fun updateNote(id: Long, title: String, content: String, isFavorite: Boolean) = viewModelScope.launch {
        repository.updateNote(id, title, content, isFavorite)
    }

    fun deleteNote(id: Long) = viewModelScope.launch {
        repository.deleteNote(id)
    }
}