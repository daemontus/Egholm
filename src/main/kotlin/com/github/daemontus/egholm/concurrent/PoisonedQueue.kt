package com.github.daemontus.egholm.concurrent

import com.github.daemontus.egholm.functional.Maybe
import com.github.daemontus.egholm.thread.GuardedThread
import java.util.concurrent.BlockingQueue


fun <T> BlockingQueue<Maybe<T>>.guardedThreadUntilPoisoned(onItem: (T) -> Unit): GuardedThread {
    return GuardedThread(threadUntilPoisoned(onItem))
}

/**
 * Create a thread listening to all items in a blocking queue until Nothing is received.
 * (Classic poison pill principle)
 */
fun <T> BlockingQueue<Maybe<T>>.threadUntilPoisoned(onItem: (T) -> Unit): Thread {
    val result = Thread {
        var job = this.take()
        while (job is Maybe.Just) {
            onItem(job.value)
            job = this.take()
        }   //got Nothing
    }
    result.start()
    return result
}

/**
 * And poison it afterwards.
 */
fun <T: Any> BlockingQueue<Maybe<T>>.poison() = this.put(Maybe.Nothing())