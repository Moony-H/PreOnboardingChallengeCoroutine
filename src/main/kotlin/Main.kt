import kotlin.system.*
import kotlinx.coroutines.*

fun main() {
    //시간을 반환하는 함수이다. 블록 안에서 걸린 시간을 반환한다.
    val time = measureTimeMillis {
        //잘 아는 runBlocking이다. 동기식 코드에서 사용하며, 블록 안의 코드가 완료될 때까지 기다린다.
        runBlocking {
            println("Weather forecast")
            printForecast()
            printTemperature()
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}

//일반 함수도 코루틴 블록 내에서 실행할 수 있지만, delay를 사용하려면 함수가 일시정지 가능한 함수인 suspend 함수여야 한다.
suspend fun printForecast() {
    delay(1000)
    println("Sunny")
}
//마찬가지
suspend fun printTemperature() {
    delay(1000)
    println("30\u00b0C")
}