package com.github.daemontus.egholm.collections

/**
 * Repeat given object specified number of times.
 */
fun <T> T.repeat(times: Int): Iterable<T> = object : Iterable<T> {

    override fun iterator(): Iterator<T> = object : Iterator<T> {
        var index = 0

        override fun hasNext(): Boolean = index < times

        override fun next(): T {
            index += 1
            return this@repeat
        }
    }

}

/**
 * Returns a list of given size with items initialised to given value
 */
fun <T> listWithInitial(size: Int, initial: T): List<T> = (1..size).map { initial }

/**
 * Same as array creation, but for lists
 */
fun <T> listWithInitial(size: Int, initializer: (Int) -> T): List<T> = (0 until size).map { initializer(it) }

