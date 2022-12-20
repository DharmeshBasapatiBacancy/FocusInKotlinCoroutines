import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main(){
    runBlocking {
        val time = measureTimeMillis {
            generate().buffer().collect {
                delay(300L)
                println(it)
            }
        }
        println("Colleted in $time ms")
    }
}

fun generate() = flow {
    (1..10).forEach {
        kotlinx.coroutines.delay(100L)
        emit(it)
    }
}