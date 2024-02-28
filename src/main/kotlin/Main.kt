import kotlin.system.*
import kotlinx.coroutines.*

//chapter2
//fun main() {
//    //시간을 반환하는 함수이다. 블록 안에서 걸린 시간을 반환한다.
//    val time = measureTimeMillis {
//        //잘 아는 runBlocking이다. 동기식 코드에서 사용하며, 블록 안의 코드가 완료될 때까지 기다린다.
//        runBlocking {
//            println("Weather forecast")
//            printForecast()
//            printTemperature()
//        }
//    }
//    println("Execution time: ${time / 1000.0} seconds")
//}
//
////일반 함수도 코루틴 블록 내에서 실행할 수 있지만, delay를 사용하려면 함수가 일시정지 가능한 함수인 suspend 함수여야 한다.
//suspend fun printForecast() {
//    delay(1000)
//    println("Sunny")
//}
////마찬가지
//suspend fun printTemperature() {
//    delay(1000)
//    println("30\u00b0C")
//}




//chapter3
//fun main() {
//    runBlocking {
//        println("Weather forecast")
//        println(getWeatherReport())
//        println("Have a good day!")
//    }
//}
//
////이것을 병렬분해라고 한다.
////coroutineScope 함수는 이 함수를 사용하는 scope의 coroutine context를 상속 받는다.
////이 예제에서는 runBlocking의 coroutine context를 받는다.
//suspend fun getWeatherReport() = coroutineScope {
//    //launch와는 다르게 결과 값을 반환할 수 있는 async 이다. 값으로 Deferred를 반환한다.
//    //Deferred는 launch가 반환하는 Job을 상속한 클래스이다. 다만 반환값이 있다.
//    val forecast = async { getForecast() }
//    val temperature = async { getTemperature() }
//    //await은 deferred가 끝날때 까지 기다리고, 그 결과값을 반환한다. 이곳에서는 두 deferred 모두를 기다리기 때문에 둘다 기다린 후 string을 반환한다.
//    "${forecast.await()} ${temperature.await()}"
//}
//
//suspend fun getForecast(): String {
//    delay(1000)
//    return "Sunny"
//}
//
//suspend fun getTemperature(): String {
//    delay(1000)
//    return "30\u00b0C"
//}




//chapter 4

//fun main() {
//    runBlocking {
//        println("Weather forecast")
//        println(getWeatherReport())
//        println("Have a good day!")
//    }
//}
//
//suspend fun getWeatherReport() = coroutineScope {
//    val forecast = async { getForecast() }
//    val temperature = async {
//        //예외처리를 하는 try catch문도 아래와 같이 coroutine 안에 넣을 수 있다.
//        try {
//            getTemperature()
//        } catch (e: AssertionError) {
//            println("Caught exception $e")
//            "{ No temperature found }"
//        }
//    }
//
//    "${forecast.await()} ${temperature.await()}"
//}
//
//suspend fun getForecast(): String {
//    delay(1000)
//    return "Sunny"
//}
//
//suspend fun getTemperature(): String {
//    delay(500)
//    throw AssertionError("Temperature is invalid")
//    return "30\u00b0C"
//}



//fun main() {
//    runBlocking {
//        println("Weather forecast")
//        println(getWeatherReport())
//        println("Have a good day!")
//    }
//}
//
//suspend fun getWeatherReport() = coroutineScope {
//    val forecast = async { getForecast() }
//    val temperature = async { getTemperature() }
//
//    delay(200)
//
//    //코루틴을 취소하는 cancel()함수이다.
//    //코루틴은 취소될 수 있지만 같은 범위의 다른 코루틴에는 영향이 없고 상위 코루틴은 취소되지 않는다.
//    temperature.cancel()
//
//    forecast.await()
//}
//
//suspend fun getForecast(): String {
//    delay(1000)
//    return "Sunny"
//}
//
//suspend fun getTemperature(): String {
//    delay(1000)
//    return "30\u00b0C"
//}

//chapter 5

fun main() {
    runBlocking {
        println("${Thread.currentThread().name} - runBlocking function")
        launch {
            println("${Thread.currentThread().name} - launch function")
            withContext(Dispatchers.Default) {
                println("${Thread.currentThread().name} - withContext function")
                delay(1000)
                println("10 results found.")
            }
            println("${Thread.currentThread().name} - end of launch function")
        }
        println("Loading...")
    }
}
