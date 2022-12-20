import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        //usingTryCatch()
        //usingCatch()
        usingOnCompletion()
    }
}

suspend fun usingOnCompletion() {
    (1..5).asFlow()
        .onEach { check(it != 2) }
        .onCompletion { e ->
            if (e != null) {
                println("Flow Completed with exception $e")
            } else {
                println("Flow completed successfully")
            }
        }
        .catch { e -> println("Caught Exception - $e") }
        .collect { println(it) }
}

suspend fun usingCatch() {
    (1..5).asFlow()
        .onEach { check(it != 2) }
        .catch { e -> println("Caught Exception - $e") }
        .collect { println(it) }
}

suspend fun usingTryCatch() {
    try {
        (1..5).asFlow()
            .onEach { check(it != 3) }
            .collect { println(it) }
    } catch (e: Exception) {
        println("Caught Exception - $e")
    }
}