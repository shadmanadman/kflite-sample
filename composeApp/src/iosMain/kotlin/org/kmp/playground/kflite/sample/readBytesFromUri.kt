package org.kmp.playground.kflite.sample
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.refTo
import kotlinx.cinterop.usePinned
import platform.Foundation.*

@OptIn(ExperimentalForeignApi::class)
actual suspend fun readBytesFromUri(uri: String): ByteArray {
    val nsData = NSData.dataWithContentsOfFile(uri)
        ?: error("Failed to load file from uri: $uri")

    val length = nsData.length.toInt()
    val byteArray = ByteArray(length)

    byteArray.usePinned {
        nsData.getBytes(it.addressOf(0), length.toULong())
    }

    return byteArray
}