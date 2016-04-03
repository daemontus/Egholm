package com.github.daemontus.egholm.functional

/**
 * Haskell style Maybe
 * Note: We need this because queues don't accept null values.
 */
sealed class Maybe<T> {

    class Just<T>(val value: T): Maybe<T>() {
        override fun equals(other: Any?): Boolean = other is Just<*> && value == other.value
        override fun hashCode(): Int = value?.hashCode() ?: 17
    }

    class Nothing<T>(): Maybe<T>() {
        override fun equals(other: Any?): Boolean = other is Nothing<*>
        override fun hashCode(): Int = 23
    }
}