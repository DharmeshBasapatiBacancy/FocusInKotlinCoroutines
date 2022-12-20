import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val tickerChannel = ticker(100L)
        launch {
            val startTime = System.currentTimeMillis()
            tickerChannel.consumeEach {
                val delta = System.currentTimeMillis()
                println("Recd tick after $delta")
            }
        }

        delay(1000L)
        println("Done")
        tickerChannel.cancel()
    }
}