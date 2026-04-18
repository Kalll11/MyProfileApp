package com.namakamu.myprofileapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ============================================================
// LATIHAN 1: Profile Card
// Composable reusable untuk menampilkan foto, nama, dan bio
// ============================================================

@Composable
fun ProfileCard(
    name: String,           // Parameter wajib: nama pengguna
    bio: String,            // Parameter wajib: deskripsi singkat
    avatarUrl: String = ""  // Parameter opsional: URL foto (kosong = pakai icon)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),         // Jarak card dari tepi layar
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)      // Padding di dalam card
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically  // Semua item rata tengah vertikal
        ) {
            // --- AVATAR (kiri) ---
            // Karena tidak pakai library Coil, kita gunakan Icon bawaan Material
            Box(
                modifier = Modifier
                    .size(64.dp)                        // Ukuran avatar 64x64
                    .clip(CircleShape)                  // Bentuk lingkaran
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Avatar $name",
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))  // Jarak antara avatar dan teks

            // --- INFO (kanan) ---
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,       // Nama tampil tebal
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = bio,
                    fontSize = 14.sp,
                    color = Color.Gray                  // Bio tampil abu-abu
                )
            }
        }
    }
}

// --- PREVIEW agar bisa langsung dilihat di Android Studio ---
@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    ProfileCard(
        name = "John Doe",
        bio = "Mobile Developer"
    )
}