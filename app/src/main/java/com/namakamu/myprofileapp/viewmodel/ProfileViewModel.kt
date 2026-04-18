package com.namakamu.myprofileapp.viewmodel

import androidx.lifecycle.ViewModel
import com.namakamu.myprofileapp.data.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    // State internal yang bisa diubah
    private val _uiState = MutableStateFlow(ProfileUiState())
    // State eksternal yang hanya bisa dibaca oleh UI
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // Fungsi untuk update nama dan bio (Fitur Edit) [cite: 1231, 1233]
    fun updateProfile(newName: String, newBio: String) {
        _uiState.update { it.copy(name = newName, bio = newBio) }
    }

    fun saveProfile(newName: String, newBio: String, newEmail: String, newPhone: String) {
        _uiState.update {
            it.copy(
                name = newName,
                bio = newBio,
                email = newEmail,
                phone = newPhone
            )
        }
    }
    // Fungsi untuk toggle Dark Mode [cite: 1236]
    fun toggleDarkMode(isEnabled: Boolean) {
        _uiState.update { it.copy(isDarkMode = isEnabled) }
    }
}