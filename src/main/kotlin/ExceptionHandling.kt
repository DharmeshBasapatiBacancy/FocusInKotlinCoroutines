import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val myHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception handled = ${throwable.localizedMessage}")
        }
        val job = GlobalScope.launch(myHandler) {
            println("Throwing exception from Job")
            throw IndexOutOfBoundsException("That localized message")
        }
        job.join()

        val deferred = GlobalScope.async {
            println("Throwing exception from async")
            throw ArithmeticException("Arithmetic Message")
        }
        try {
            deferred.await()
        } catch (e: ArithmeticException) {
            println("Caught Arithmetic Exception ${e.localizedMessage}")
        }
    }
}