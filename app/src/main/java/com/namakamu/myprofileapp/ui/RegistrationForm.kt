package com.namakamu.myprofileapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// 1. Stateless Component (Reusable) [cite: 1140]
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

// 2. Parent yang menyimpan state [cite: 1127]
@Composable
fun RegistrationForm() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Pendaftaran", style = MaterialTheme.typography.headlineMedium)

        // Gunakan komponen yang sama untuk dua field berbeda
        LabeledTextField(label = "Nama Lengkap", value = name, onValueChange = { name = it })
        LabeledTextField(label = "Email", value = email, onValueChange = { email = it })

        // Preview data secara realtime
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
            Text(
                text = "Hello, $name!\nEmail kamu: $email",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}