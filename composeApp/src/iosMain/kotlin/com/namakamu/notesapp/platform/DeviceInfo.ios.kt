package com.namakamu.notesapp.platform // Sesuaikan jika nama package Anda berbeda

import platform.UIKit.UIDevice

actual class DeviceInfo actual constructor() {
    actual fun getDeviceName(): String {
        return UIDevice.currentDevice.name
    }

    actual fun getOsVersion(): String {
        return "${UIDevice.currentDevice.systemName} ${UIDevice.currentDevice.systemVersion}"
    }
}