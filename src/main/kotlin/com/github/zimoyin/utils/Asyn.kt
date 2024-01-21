package com.github.zimoyin.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * 虚拟线程
 */
fun virtual(callback: () -> Unit): Thread {
    return Thread.startVirtualThread {
        Thread.currentThread().name = "VirtualThread-${Thread.currentThread().threadId()}"
        callback()
    }
}

/**
 * 创建虚拟线程池
 */
fun createVirtualTool(): ExecutorService = Executors.newVirtualThreadPerTaskExecutor()

/**
 * 切换当前协程的调度到IO
 */
suspend fun withIO(callback: suspend () -> Unit) {
    return withContext(Dispatchers.IO) {
        callback()
    }
}

@Composable
fun CoroutineLaunch(coroutineScope: CoroutineScope = rememberCoroutineScope(),callback: suspend () -> Unit): Job {
    return coroutineScope.launch {
        callback()
    }
}

fun IO(callback: suspend () -> Unit): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        callback()
    }
}

class Future<T>(
    private val future: CompletableFuture<T> = CompletableFuture<T>(),
) {

    private var cache: T? = null

    fun onSuccess(callback: (T) -> Unit): Future<T> {
        future.thenAccept(callback)
        return this
    }

    fun onFailure(callback: (Throwable) -> Unit): Future<T> {
        future.exceptionally {
            callback(it)
            null
        }
        return this
    }

    fun get(): T? {
        return cache
    }

    fun await(): T {
        return future.get()
    }

    @Deprecated("超时抛出异常，但是不是停止任务")
    fun await(timestamp: Long): T {
        return this.setTimeout(timestamp).await()
    }

    fun complete(t: T): Boolean {
        return future.complete(t)
    }

    fun fail(ex: Throwable): Boolean {
        return future.completeExceptionally(ex)
    }

    @Deprecated("超时抛出异常，但是不是停止任务")
    fun setTimeout(time: Long, unit: TimeUnit): Future<T> {
        future.orTimeout(time, unit)
        return this
    }

    @Deprecated("超时抛出异常，但是不是停止任务")
    fun setTimeout(timestamp: Long): Future<T> {
        future.orTimeout(timestamp, TimeUnit.MILLISECONDS)
        return this
    }
}
