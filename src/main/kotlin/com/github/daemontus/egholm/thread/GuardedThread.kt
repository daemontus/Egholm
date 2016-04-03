package com.github.daemontus.egholm.thread

/**
 * A thread that will catch exceptions that are thrown inside of it and throw them again later on, when the thread is joined.
 */
class GuardedThread(
        val thread: Thread
) {

    constructor(task: () -> Unit) : this(Thread(task))

    private var ex: Throwable? = null

    init {
        thread.setUncaughtExceptionHandler { thread, throwable ->
            ex = throwable
            //sometimes, thread won't join because of this exception (like when termination detection fails)
            //Print it so that we at least know something is wrong.
            System.err.println("Uncaught exception in $thread")
            ex?.printStackTrace()
        }
    }

    fun join() {
        thread.join()
        ex?.let { throw it }
    }

}

/**
 * Create a new guarded thread and immediately start it
 */
fun guardedThread(task: () -> Unit) = GuardedThread(task).apply { this.thread.start() }
