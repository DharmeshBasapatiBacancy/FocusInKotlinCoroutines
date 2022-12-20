import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main(){
    runBlocking {
        val firstDeferred = async {
            getFirstValue()
        }
        val secondDeferred = async {
            getSecondValue()
        }
        println("Doing some processing here")
        delay(1000L)
        println("Waiting for values")

        val firstValue = firstDeferred.await()
        val secondValue = secondDeferred.await()

        println("Total = ${firstValue + secondValue}")

    }
}

suspend fun getFirstValue(): Int {
    delay(2000L)
    val value = Random.nextInt(100)
    println("Returning first value $value")
    return value
}

suspend fun getSecondValue(): Int {
    delay(3000L)
    val value = Random.nextInt(1000)
    println("Returning 2nd Value $value")
    return value
}