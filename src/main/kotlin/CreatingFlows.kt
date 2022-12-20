import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        sendNumbersUsingFlowBuilder().collect {
            println("#1 Recd number $it")
        }

        sendNumbersUsingAsFlow().collect {
            println("#2 Recd number $it")
        }

        sendNumberUsingFlowOf().collect {
            println("#3 Recd number $it")
        }
    }
}

fun sendNumbersUsingFlowBuilder() = flow {
    for (i in 1..10) {
        emit(i)
    }
}

fun sendNumbersUsingAsFlow() = listOf(1, 2, 3, 4, 5).asFlow()

fun sendNumberUsingFlowOf() = flowOf(1, 2, 3, 4, 5)