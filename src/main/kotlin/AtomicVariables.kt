import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val counter = AtomicInteger(0)
        withContext(Dispatchers.Default) {
            massiveRunUsingAtomicVariable { counter.incrementAndGet() }
        }
        println("Counter = $counter")
    }
}

suspend fun massiveRunUsingAtomicVariable(action: suspend () -> Unit) {
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
