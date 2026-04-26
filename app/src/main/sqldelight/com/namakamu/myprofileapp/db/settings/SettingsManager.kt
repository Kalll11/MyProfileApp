package com.namakamu.myprofileapp.settings

import android.content.Context
import com.russhwolf.settings.SharedPreferencesSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.flow.Flow

class SettingsManager(context: Context) {
    private val delegate = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
    private val settings = SharedPreferencesSettings(delegate)
    private val flowSettings: FlowSettings = settings.toFlowSettings()

    val themeFlow: Flow<String> = flowSettings.getStringFlow("app_theme", "System")
    val sortOrderFlow: Flow<String> = flowSettings.getStringFlow("sort_order", "Newest")

    suspend fun setTheme(theme: String) {
        flowSettings.putString("app_theme", theme)
    }

    suspend fun setSortOrder(order: String) {
        flowSettings.putString("sort_order", order)
    }
}