import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
  runBlocking {
      launch(CoroutineName("MyCoRo")) {
          println("This is run from ${coroutineContext.get(CoroutineName.Key)}")
      }

      GlobalScope.launch {

      }
  }
}