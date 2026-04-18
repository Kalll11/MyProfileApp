package com.namakamu.myprofileapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.namakamu.myprofileapp.data.Note
import com.namakamu.myprofileapp.viewmodel.NoteViewModel

// ==========================================
// 1. DAFTAR CATATAN (TAB NOTES)
// ==========================================
@Composable
fun NoteListScreen(
    viewModel: NoteViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    val notes by viewModel.notes.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Catatan Saya", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        if (notes.isEmpty()) {
            Text("Belum ada catatan. Klik tombol + untuk menambah.", color = Color.Gray)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(notes) { note ->
                    NoteCard(note = note, viewModel = viewModel, onClick = { onNavigateToDetail(note.id) })
                }
            }
        }
    }
}

// ==========================================
// 2. DAFTAR FAVORIT (TAB FAVORITES)
// ==========================================
@Composable
fun FavoritesScreen(
    viewModel: NoteViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    // Filter catatan yang difavoritkan
    val notes by viewModel.notes.collectAsState()
    val favoriteNotes = notes.filter { it.isFavorite }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Catatan Favorit", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteNotes.isEmpty()) {
            Text("Belum ada catatan favorit.", color = Color.Gray)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(favoriteNotes) { note ->
                    NoteCard(note = note, viewModel = viewModel, onClick = { onNavigateToDetail(note.id) })
                }
            }
        }
    }
}

// ==========================================
// COMPONENT: KARTU CATATAN (DIPAKAI DI LIST & FAVORIT)
// ==========================================
@Composable
fun NoteCard(note: Note, viewModel: NoteViewModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(note.title, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(note.content, color = Color.Gray, maxLines = 1)
            }
            // Tombol Bintang Favorit
            IconButton(onClick = { viewModel.toggleFavorite(note.id) }) {
                Icon(
                    imageVector = if (note.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (note.isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}

// ==========================================
// 3. TAMBAH CATATAN (FORM ADD)
// ==========================================
@Composable
fun AddNoteScreen(viewModel: NoteViewModel, onNavigateBack: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tambah Catatan", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Judul") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = content, onValueChange = { content = it }, label = { Text("Isi Catatan") }, modifier = Modifier.fillMaxWidth(), minLines = 5)

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.addNote(title, content) // Simpan ke ViewModel
                onNavigateBack() // Kembali ke layar sebelumnya
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Catatan")
        }
    }
}

// ==========================================
// 4. DETAIL CATATAN (BACA)
// ==========================================
@Composable
fun NoteDetailScreen(noteId: Int, viewModel: NoteViewModel, onNavigateBack: () -> Unit, onNavigateToEdit: (Int) -> Unit) {
    val note = viewModel.getNoteById(noteId) // Ambil data dari ViewModel

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (note != null) {
            Text(note.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(note.content, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.weight(1f)) // Dorong tombol ke bawah

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = onNavigateBack, modifier = Modifier.weight(1f)) { Text("Kembali") }
                Button(onClick = { onNavigateToEdit(noteId) }, modifier = Modifier.weight(1f)) { Text("Edit") }
            }
        } else {
            Text("Catatan tidak ditemukan.")
            Button(onClick = onNavigateBack) { Text("Kembali") }
        }
    }
}

// ==========================================
// 5. EDIT CATATAN (FORM EDIT)
// ==========================================
@Composable
fun EditNoteScreen(noteId: Int, viewModel: NoteViewModel, onNavigateBack: () -> Unit) {
    val note = viewModel.getNoteById(noteId)

    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Edit Catatan", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Judul") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = content, onValueChange = { content = it }, label = { Text("Isi Catatan") }, modifier = Modifier.fillMaxWidth(), minLines = 5)

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.updateNote(noteId, title, content) // Update data
                onNavigateBack() // Kembali ke layar sebelumnya
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Perubahan")
        }
    }
}