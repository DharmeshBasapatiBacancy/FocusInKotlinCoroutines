import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val counterContext = newSingleThreadContext("CounterContext")
        var counter = 0
        // - Fine Grained
//        withContext(Dispatchers.Default) {
//            massiveRunUsingThreadConfinement {
//               withContext(counterContext){
//                   counter++
//               }
//            }
//        }

        // - Coarse Grained
        withContext(counterContext) {
            massiveRunUsingThreadConfinement {
                counter++
            }
        }
        println("Counter = $counter")
    }
}

suspend fun massiveRunUsingThreadConfinement(action: suspend () -> Unit) {
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
