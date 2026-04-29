package com.namakamu.notesapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Notes : BottomNavItem("notes", Icons.Default.List, "Catatan")
    object Favorites : BottomNavItem("favorites", Icons.Default.Favorite, "Favorit")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profil")
}