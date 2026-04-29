package com.namakamu.notesapp.platform

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class NetworkMonitor actual constructor() {
    actual fun observeConnectivity(): Flow<Boolean> {
        return flowOf(true) // Di iOS, kita anggap selalu terhubung
    }
}