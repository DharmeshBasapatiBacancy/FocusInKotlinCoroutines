import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

//Flows are cold
//Flows will be cancelled when the scope it lives in is cancelled
fun main() {
    runBlocking {
        val numbersFlow = sendDelayedNumbers()
        println("Flows are cold, hasn't started yet")
        println("Starting flow now...")
        withTimeoutOrNull(1500L){
            numbersFlow.collect { println(it) }
        }
    }
}

fun sendNumbers() = listOf(1, 2, 3, 4, 5).asFlow()

fun sendDelayedNumbers() = flow {
    listOf(1,2,3,4,5).forEach {
        delay(500L)
        emit(it)
    }
}