// Interfaceを利用したコールバック

interface CallBackListener {
    fun onFinishedHoge(isSuccess: Boolean, errorCode: String)
}

class User() {
    fun execute(callback: CallBackListener) {
        println("execute")
        callback.onFinishedHoge(true, "NO_ERROR")
    }
}

fun main() {
    val user = User()
    user.execute(object : CallBackListener {
        override fun onFinishedHoge(isSuccess: Boolean, errorCode: String) {
            println("complete!!! ${isSuccess}")
        }
    }) 
}