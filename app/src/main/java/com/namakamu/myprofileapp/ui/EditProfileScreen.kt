package com.namakamu.myprofileapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.* // Ini yang akan memperbaiki error getValue/setValue dan 'it'
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.namakamu.myprofileapp.viewmodel.ProfileViewModel

@Composable
fun EditProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // State lokal untuk form
    var name by remember { mutableStateOf(uiState.name) }
    var bio by remember { mutableStateOf(uiState.bio) }
    var email by remember { mutableStateOf(uiState.email) }
    var phone by remember { mutableStateOf(uiState.phone) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
        Text("Edit Profil", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nama") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = bio, onValueChange = { bio = it }, label = { Text("Bio") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Telepon") }, modifier = Modifier.fillMaxWidth())

        Button(
            onClick = {
                viewModel.saveProfile(name, bio, email, phone)
                onNavigateBack()
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Simpan Perubahan")
        }
    }
}