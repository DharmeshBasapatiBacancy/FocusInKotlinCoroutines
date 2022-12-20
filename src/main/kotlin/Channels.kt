import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            (1..5).forEach {
                channel.send(it * it)
            }
            channel.close()
        }

//        (1..3).forEach {
//            println(channel.receive())
//        }
        for(i in channel)
            println(i)
    }
}