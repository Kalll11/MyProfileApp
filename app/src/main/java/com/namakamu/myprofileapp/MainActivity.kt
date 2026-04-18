package com.namakamu.myprofileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.namakamu.myprofileapp.components.BottomNavigationBar
import com.namakamu.myprofileapp.navigation.BottomNavItem
import com.namakamu.myprofileapp.navigation.Screen
import com.namakamu.myprofileapp.screens.*
import com.namakamu.myprofileapp.ui.EditProfileScreen
import com.namakamu.myprofileapp.ui.ProfileScreen
import com.namakamu.myprofileapp.ui.theme.MyProfileAppTheme
import com.namakamu.myprofileapp.viewmodel.ProfileViewModel
// [BARU] Import NoteViewModel
import com.namakamu.myprofileapp.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProfileAppTheme {
                MainAppNavigation()
            }
        }
    }
}

@Composable
fun MainAppNavigation() {
    val navController = rememberNavController()
    val profileViewModel: ProfileViewModel = viewModel() // ViewModel dari Minggu 4
    val noteViewModel: NoteViewModel = viewModel()

    // Membaca rute aktif untuk menyembunyikan BottomBar & FAB di layar tertentu
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Daftar rute yang menggunakan BottomBar
    val bottomBarRoutes = listOf(BottomNavItem.Notes.route, BottomNavItem.Favorites.route, BottomNavItem.Profile.route)

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavigationBar(navController = navController)
            }
        },
        floatingActionButton = {
            // FAB hanya muncul di tab Notes
            if (currentRoute == BottomNavItem.Notes.route) {
                FloatingActionButton(onClick = { navController.navigate(Screen.AddNote.route) }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Note")
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Notes.route, // Mulai dari Tab Notes
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            // ================= 3 TABS UTAMA =================
            composable(BottomNavItem.Notes.route) {
                NoteListScreen(
                    viewModel = noteViewModel,
                    onNavigateToDetail = { id -> navController.navigate(Screen.NoteDetail.createRoute(id)) }
                )
            }
            composable(BottomNavItem.Favorites.route) {
                FavoritesScreen(
                    viewModel = noteViewModel,
                    onNavigateToDetail = { id -> navController.navigate(Screen.NoteDetail.createRoute(id)) }
                )
            }
            composable(BottomNavItem.Profile.route) {
                ProfileScreen(
                    viewModel = profileViewModel,
                    onNavigateToEdit = { navController.navigate(Screen.EditProfile.route) }
                )
            }

            // ================= LAYAR DETAIL & FORM =================
            composable(Screen.EditProfile.route) {
                EditProfileScreen(
                    viewModel = profileViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(Screen.AddNote.route) {
                AddNoteScreen(
                    viewModel = noteViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            // Menerima Argumen (Note ID) untuk Detail
            composable(
                route = Screen.NoteDetail.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                NoteDetailScreen(
                    noteId = noteId,
                    viewModel = noteViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToEdit = { id -> navController.navigate(Screen.EditNote.createRoute(id)) }
                )
            }

            // Menerima Argumen (Note ID) untuk Edit
            composable(
                route = Screen.EditNote.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                EditNoteScreen(
                    noteId = noteId,
                    viewModel = noteViewModel, // [BARU] 6. Suntikkan ke EditNote
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}