import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

//Other 2 operators are toList and toSet
fun main(){
    runBlocking {
        //mapOperator()
        //filterOperator()
        //transformOperator()
        //takeOperator()
        //reduceOperator()
        flowOnOperator()
    }
}

suspend fun flowOnOperator(){
    (1..10).asFlow()
        .flowOn(Dispatchers.IO)
        .collect {
            println(it)
        }
}

suspend fun reduceOperator(){
    val size = 5
    val factorial = (1..size).asFlow()
        .reduce { accumulator, value ->
            println("Accumulator($value) is $accumulator")
            accumulator * value
        }
    println("Factorial of $size is $factorial")
}

suspend fun takeOperator(){
    (1..10).asFlow()
        .take(4)
        .collect { println(it) }
}

suspend fun transformOperator() {
    (1..10).asFlow()
        .transform {
            emit("Emitting Value $it")
            emit(it)
        }
        .collect {
            println(it)
        }
}

suspend fun filterOperator(){
    (1..10).asFlow()
        .filter { it % 2 == 0 }
        .collect { println(it) }
}

suspend fun mapOperator(){
    (1..10).asFlow()
        .map {
            delay(500L)
            "mapping $it"
        }
        .collect {
            println(it)
        }
}