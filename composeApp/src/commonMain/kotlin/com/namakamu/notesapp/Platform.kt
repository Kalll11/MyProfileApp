package com.namakamu.notesapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform