package com.namakamu.myprofileapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.namakamu.myprofileapp.data.NoteRepository
import com.namakamu.myprofileapp.db.db.Note
import com.namakamu.myprofileapp.settings.SettingsManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// Tambahan untuk memenuhi Syarat Tugas No. 6 (UI States)
sealed class UiState {
    object Loading : UiState()
    object Empty : UiState()
    data class Content(val notes: List<Note>) : UiState()
}

// Menggunakan AndroidViewModel agar otomatis mendapatkan Context tanpa merusak UI Anda
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    // Menyambungkan ViewModel langsung ke Database Lokal dan Settings
    private val repository = NoteRepository(application)
    val settingsManager = SettingsManager(application)

    // Untuk fitur Search (Syarat Tugas No. 3)
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    // 1. Ambil data LANGSUNG dari SQLite (Syarat Tugas No. 5 - Offline First)
    // Fitur Sort (Settings) dan Search langsung digabung di sini!
    @OptIn(ExperimentalCoroutinesApi::class)
    val notes: StateFlow<List<Note>> = combine(
        settingsManager.sortOrderFlow,
        _searchQuery
    ) { sortOrder, query -> Pair(sortOrder, query) }
        .flatMapLatest { (sortOrder, query) ->
            val isDesc = sortOrder == "Newest"
            if (query.isBlank()) repository.getAllNotes(isDesc)
            else repository.searchNotes(query)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // State tambahan jika Anda ingin mengimplementasikan UI Loading/Empty di Screen
    val uiState: StateFlow<UiState> = notes.map {
        if (it.isEmpty()) UiState.Empty else UiState.Content(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    // Fungsi untuk memperbarui teks pencarian dari SearchBar di UI
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // ====================================================================
    // FUNGSI-FUNGSI LAMA ANDA YANG DIPERTAHANKAN (TAPI KINI TERSAMBUNG KE DB)
    // ====================================================================

    // 1. Tambah Catatan (Simpan permanen)
    fun addNote(title: String, content: String) {
        if (title.isNotBlank() || content.isNotBlank()) {
            viewModelScope.launch {
                repository.insertNote(title, content, false)
            }
        }
    }

    // 2. Edit Catatan (Update ke DB dengan konversi Int ke Long otomatis)
    fun updateNote(id: Int, newTitle: String, newContent: String) {
        viewModelScope.launch {
            val oldNote = getNoteById(id)
            if (oldNote != null) {
                repository.updateNote(id.toLong(), newTitle, newContent, oldNote.isFavorite)
            }
        }
    }

    // 3. Toggle Favorit (Bintang)
    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            val oldNote = getNoteById(id)
            if (oldNote != null) {
                repository.updateNote(oldNote.id, oldNote.title, oldNote.content, !oldNote.isFavorite)
            }
        }
    }

    // 4. Ambil 1 Catatan berdasarkan ID
    fun getNoteById(id: Int): Note? {
        // Kita bandingkan id (Long) dari database dengan id (Int) dari UI Anda
        return notes.value.find { it.id == id.toLong() }
    }
}