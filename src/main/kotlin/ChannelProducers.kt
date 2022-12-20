import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        produceCubes().consumeEach { println(it) }
    }
}

fun CoroutineScope.produceCubes() = produce {
    for(x in 1..5){
        send(x * x * x)
    }
}