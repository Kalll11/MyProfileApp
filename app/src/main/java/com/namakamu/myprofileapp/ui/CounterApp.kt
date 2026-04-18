package com.namakamu.myprofileapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterApp() {
    // 1. Deklarasi state dengan remember & mutableStateOf
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 2. Display nilai count
        Text(text = "Nilai: $count", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // 3. Tombol Decrement (dengan fitur Disable jika 0)
            Button(
                onClick = { if (count > 0) count-- },
                enabled = count > 0 // Checklist: Disable decrement jika 0 [cite: 1108]
            ) {
                Text("-1")
            }

            // 4. Tombol Increment
            Button(onClick = { count++ }) {
                Text("+1")
            }

            // 5. Tombol Reset
            OutlinedButton(onClick = { count = 0 }) {
                Text("Reset")
            }
        }
    }
}