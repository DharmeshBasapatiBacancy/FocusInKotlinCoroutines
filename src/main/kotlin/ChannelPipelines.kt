import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val numbers = produceNumbers()
        val cubes = cubeOut(numbers)
        (1..5).forEach {
            println(cubes.receive())
        }
        println("Done !!!")
        coroutineContext.cancelChildren()
    }
}

fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true)
        send(x++)
}

fun CoroutineScope.cubeOut(numbers: ReceiveChannel<Int>) = produce {
    numbers.consumeEach { send(it * it * it) }
}