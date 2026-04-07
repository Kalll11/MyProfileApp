package com.namakamu.myprofileapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ============================================================
// LATIHAN 2: Login Form
// Form login dengan username, password, dan tombol login
// ============================================================

@Composable
fun LoginForm(
    onLogin: (String, String) -> Unit = { _, _ -> }  // Callback saat tombol Login ditekan
) {
    // State: menyimpan teks yang diketik user
    // 'remember' agar nilai tidak hilang saat recomposition
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)  // Jarak antar elemen 16dp
    ) {
        // --- JUDUL ---
        Text(
            text = "Login",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- INPUT USERNAME ---
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },   // Update state setiap ketik
            label = { Text("Username") },
            placeholder = { Text("Masukkan username") },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Username icon")
            },
            singleLine = true,                   // Hanya satu baris
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next       // Tombol Next di keyboard
            )
        )

        // --- INPUT PASSWORD ---
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Masukkan password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password icon")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),  // Tampilkan sebagai ••••
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- TOMBOL LOGIN ---
        Button(
            onClick = { onLogin(username, password) },  // Panggil callback dengan data input
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = username.isNotEmpty() && password.isNotEmpty()  // Aktif jika field terisi
        ) {
            Text(
                text = "LOGIN",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    LoginForm()
}