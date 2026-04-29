package com.namakamu.notesapp.platform

expect class DeviceInfo() {
    fun getDeviceName(): String
    fun getOsVersion(): String
}