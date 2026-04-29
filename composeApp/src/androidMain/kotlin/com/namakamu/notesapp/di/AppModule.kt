package com.namakamu.notesapp.di

import com.namakamu.notesapp.data.NoteRepository
import com.namakamu.notesapp.platform.DeviceInfo
import com.namakamu.notesapp.platform.NetworkMonitor
import com.namakamu.notesapp.viewmodel.NoteViewModel
import com.namakamu.notesapp.viewmodel.ProfileViewModel // 👉 TAMBAHKAN IMPORT INI

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // 1. Platform Services (Modul 8 KMP)
    single { DeviceInfo() }
    single { NetworkMonitor() }

    // 2. Repository (Database)
    single { NoteRepository(androidContext()) }

    // 3. ViewModel
    viewModel { NoteViewModel(get()) }
    viewModel { ProfileViewModel() } // 👉 TAMBAHKAN BARIS INI
}