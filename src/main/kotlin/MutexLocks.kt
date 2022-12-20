import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val mutex = Mutex()
        val counter = AtomicInteger(0)
        withContext(Dispatchers.Default) {
            massiveRunUsingMutex {
                mutex.withLock {
                    counter.incrementAndGet()
                }
            }
        }
        println("Counter = $counter")
    }
}

suspend fun massiveRunUsingMutex(action: suspend () -> Unit) {
    val n = 100
    val k = 2000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}
