import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        val job1 = launch {
            //delay(2000L)
            println("Job 1 Launched")
            val job2 = launch {
                println("Job 2 Launched")
                delay(3000L)
                println("Job 2 is finishing")
            }
            job2.invokeOnCompletion { println("Job 2 is Completed") }

            val job3 = launch {
                println("Job 3 Launched")
                delay(3000L)
                println("Job 3 is finishing")
            }
            job3.invokeOnCompletion { println("Job 3 is Completed") }
        }

        job1.invokeOnCompletion {
            println("Job 1 is Completed")
        }

        delay(2500L)
        println("Job 1 will be cancelled")
        job1.cancel()
    }
}