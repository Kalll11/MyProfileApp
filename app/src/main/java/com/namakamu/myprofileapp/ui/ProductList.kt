package com.namakamu.myprofileapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
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
// LATIHAN 3: Product List
// Tampilan list produk dengan gambar, nama, dan harga
// ============================================================

// Data class untuk menyimpan info produk
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String = ""
)

// --- Composable 1: Item produk tunggal ---
@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- GAMBAR PRODUK (kiri, 80x80dp) ---
            // Karena tidak pakai Coil, gunakan Box + Icon sebagai placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Gambar ${product.name}",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // --- INFO PRODUK (kanan) ---
            Column(
                modifier = Modifier.weight(1f),  // Mengisi sisa ruang horizontal
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.price,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

// --- Composable 2: List semua produk ---
@Composable
fun ProductList(products: List<Product>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Header list
        Text(
            text = "Daftar Produk",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        // Tampilkan setiap produk
        products.forEach { product ->
            ProductItem(product = product)
        }
    }
}

// --- Data dummy untuk preview ---
val sampleProducts = listOf(
    Product(1, "Laptop Gaming ASUS", "Rp 15.000.000"),
    Product(2, "Mouse Wireless Logitech", "Rp 350.000"),
    Product(3, "Keyboard Mechanical", "Rp 800.000"),
    Product(4, "Monitor 27\" 144Hz", "Rp 4.500.000"),
    Product(5, "Headset Gaming RGB", "Rp 250.000")
)

// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun ProductListPreview() {
    ProductList(products = sampleProducts)
}