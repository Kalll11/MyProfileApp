package com.namakamu.notesapp.data

data class ProfileUiState(
    val name: String = "Nama Kamu",
    val title: String = "Mahasiswa Teknik Informatika",
    val bio: String = "Mahasiswa aktif Teknik Informatika ITERA yang tertarik pada pengembangan aplikasi mobile.",
    val email: String = "namakamu@student.itera.ac.id",
    val phone: String = "+62 812-3456-7890",
    val location: String = "Lampung Selatan, Indonesia",
    val isDarkMode: Boolean = false // Fitur Dark Mode Toggle [cite: 1235]
)