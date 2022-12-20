import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var functionCalls = 0

fun main(){
    GlobalScope.launch { completeMessage() }
    GlobalScope.launch { improveMessage() }
    print("Hello, ")
    Thread.sleep(2000L)
    println("There have been $functionCalls calls so far")
}

suspend fun completeMessage(){
    delay(1000L)
    println("World !!!")
    functionCalls++
}

suspend fun improveMessage(){
    delay(500L)
    println("Suspend Fucntions are Cool!!!")
    functionCalls++
}
