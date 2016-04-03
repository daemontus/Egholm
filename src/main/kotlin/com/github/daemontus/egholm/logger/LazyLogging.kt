package com.github.daemontus.egholm.logger

import java.util.logging.Level
import java.util.logging.Logger

/*
 * Logger extensions for painless lazy logging
 */

inline fun Logger.lSevere(action: () -> String) {
    if (this.isLoggable(Level.SEVERE)) {
        this.severe(action())
    }
}

inline fun Logger.lWarning(action: () -> String) {
    if (this.isLoggable(Level.WARNING)) {
        this.info(action())
    }
}

inline fun Logger.lInfo(action: () -> String) {
    if (this.isLoggable(Level.INFO)) {
        this.info(action())
    }
}

inline fun Logger.lFine(action: () -> String) {
    if (this.isLoggable(Level.FINE)) {
        this.fine(action())
    }
}

inline fun Logger.lFiner(action: () -> String) {
    if (this.isLoggable(Level.FINER)) {
        this.fine(action())
    }
}

inline fun Logger.lFinest(action: () -> String) {
    if (this.isLoggable(Level.FINEST)) {
        this.finest(action())
    }
}

val silentLogger = Logger.getAnonymousLogger().apply { level = Level.OFF }
val severLogger = Logger.getAnonymousLogger().apply { level = Level.SEVERE }
val silentLoggers: (Int) -> Logger = { id -> silentLogger }
val severeLoggers: (Int) -> Logger = { id -> severLogger }