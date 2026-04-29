package com.namakamu.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.namakamu.notesapp.components.BottomNavigationBar
import com.namakamu.notesapp.components.BottomNavItem
import com.namakamu.notesapp.screens.*
import com.namakamu.notesapp.ui.*
import com.namakamu.notesapp.viewmodel.NoteViewModel
import com.namakamu.notesapp.viewmodel.ProfileViewModel

// Import Koin untuk menyuntikkan ViewModel secara otomatis
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Pintu utama kini membuka aplikasi Anda, bukan template KMP lagi!
            MainNotesApp()
        }
    }
}

@Composable
fun MainNotesApp() {
    val navController = rememberNavController()

    // Meminta Koin untuk mengambilkan ViewModel yang sudah kita daftarkan di AppModule
    val noteViewModel: NoteViewModel = koinViewModel()
    val profileViewModel: ProfileViewModel = koinViewModel()

    // Mengecek posisi tab saat ini (untuk menyembunyikan tombol + jika bukan di tab Catatan)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = {
            // Tombol "Tambah" hanya muncul di halaman List Catatan
            if (currentRoute == BottomNavItem.Notes.route) {
                FloatingActionButton(onClick = { navController.navigate("add_note") }) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah Catatan")
                }
            }
        }
    ) { innerPadding ->

        // ==========================================
        // PUSAT NAVIGASI APLIKASI (NAVHOST)
        // ==========================================
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Notes.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            // 1. Tab Utama (Daftar Catatan)
            composable(BottomNavItem.Notes.route) {
                NoteListScreen(
                    viewModel = noteViewModel,
                    onNavigateToDetail = { id -> navController.navigate("detail/$id") }
                )
            }

            // 2. Tab Favorit
            composable(BottomNavItem.Favorites.route) {
                FavoritesScreen(
                    viewModel = noteViewModel,
                    onNavigateToDetail = { id -> navController.navigate("detail/$id") }
                )
            }

            // 3. Tab Profil
            composable(BottomNavItem.Profile.route) {
                ProfileScreen(
                    viewModel = profileViewModel,
                    onNavigateToEdit = { navController.navigate("edit_profile") }
                )
            }

            // 4. Layar Tambah Catatan Baru
            composable("add_note") {
                AddNoteScreen(
                    viewModel = noteViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            // 5. Layar Detail Catatan (Membaca Isi)
            composable(
                route = "detail/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                NoteDetailScreen(
                    noteId = noteId,
                    viewModel = noteViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToEdit = { id -> navController.navigate("edit_note/$id") }
                )
            }

            // 6. Layar Edit Catatan
            composable(
                route = "edit_note/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                EditNoteScreen(
                    noteId = noteId,
                    viewModel = noteViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            // 7. Layar Edit Profil
            composable("edit_profile") {
                EditProfileScreen(
                    viewModel = profileViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}