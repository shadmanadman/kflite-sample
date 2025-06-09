package org.kmp.playground.kflite.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform