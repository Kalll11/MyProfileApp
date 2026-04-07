package com.namakamu.myprofileapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ============================================================
// TUGAS PRAKTIKUM: My Profile App
// Halaman profil lengkap dengan Header, Bio, dan Info List
// ============================================================

// --- Composable 1: Header profil (foto + nama) ---
@Composable
fun ProfileHeader(
    name: String,
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Avatar lingkaran
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto profil",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(60.dp)
                )
            }
            // Nama
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            // Jabatan/title
            Text(
                text = title,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
            )
        }
    }
}

// --- Composable 2: Item informasi (icon + label + value) ---
@Composable
fun InfoItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon info
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// --- Composable 3: Card profil utama (menggabungkan semua info) ---
@Composable
fun ProfileInfoCard(
    email: String,
    phone: String,
    location: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            // Label section
            Text(
                text = "Informasi Kontak",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Divider()
            // List informasi menggunakan InfoItem
            InfoItem(icon = Icons.Default.Email, label = "Email", value = email)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            InfoItem(icon = Icons.Default.Phone, label = "Phone", value = phone)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            InfoItem(icon = Icons.Default.LocationOn, label = "Location", value = location)
        }
    }
}

// --- HALAMAN UTAMA: Menggabungkan semua composable ---
@Composable
fun ProfileScreen() {
    // verticalScroll agar halaman bisa discroll jika konten panjang
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Header dengan foto dan nama
        ProfileHeader(
            name = "Nama Kamu",
            title = "Mahasiswa Teknik Informatika"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 2. Bio singkat
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Tentang Saya",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Mahasiswa aktif Teknik Informatika ITERA yang " +
                            "tertarik pada pengembangan aplikasi mobile. " +
                            "Sedang belajar Kotlin Multiplatform dan Compose.",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    lineHeight = 22.sp
                )
            }
        }

        // 3. Informasi kontak menggunakan ProfileInfoCard
        ProfileInfoCard(
            email = "namakamu@student.itera.ac.id",
            phone = "+62 812-3456-7890",
            location = "Lampung Selatan, Indonesia"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 4. Tombol Edit Profil (menggunakan Button)
        Button(
            onClick = { /* navigasi ke halaman edit */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(50.dp)
        ) {
            Icon(Icons.Default.Edit, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit Profil", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}