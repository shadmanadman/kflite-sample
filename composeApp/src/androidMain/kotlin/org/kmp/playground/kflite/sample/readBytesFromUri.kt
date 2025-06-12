package org.kmp.playground.kflite.sample

import java.io.File

actual suspend fun readBytesFromUri(uri: String): ByteArray {
    val file = File(uri)
    return file.readBytes()
}