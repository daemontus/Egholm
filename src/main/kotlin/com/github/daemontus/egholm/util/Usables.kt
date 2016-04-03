package com.github.daemontus.egholm.util

import java.io.Closeable

inline fun <T : Closeable, L : Iterable<T>, R> L.use(block: (L) -> R): R {
    var closed = false
    try {
        return block(this)
    } catch (e: Exception) {
        closed = true
        try {
            map {
                it.close()
            }
        } catch (closeException: Exception) {
            //e.addSuppressed(closeException)
        }
        throw e
    } finally {
        if (!closed) {
            map { it.close() }
        }
    }
}