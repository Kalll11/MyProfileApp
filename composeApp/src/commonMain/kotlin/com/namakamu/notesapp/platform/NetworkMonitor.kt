package com.namakamu.notesapp.platform

import kotlinx.coroutines.flow.Flow

expect class NetworkMonitor() {
    fun observeConnectivity(): Flow<Boolean>
}