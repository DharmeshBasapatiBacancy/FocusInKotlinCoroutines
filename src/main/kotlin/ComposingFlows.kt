import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        //zipOperation()
        combineOperation()
    }
}

suspend fun combineOperation() {
    val numbers = (1..3).asFlow().onEach { delay(300L) }
    val values = listOf("One", "Two", "Three").asFlow().onEach { delay(500L) }

    numbers.combine(values) { a, b -> "$a -> $b" }.collect { println(it) }
}

suspend fun zipOperation() {
    val english = flowOf("One", "Two", "Three")
    val hindi = flowOf("Ek", "Do", "Teen")

    english.zip(hindi) { a, b -> " $a in Hindi is $b " }.collect { println(it) }
}