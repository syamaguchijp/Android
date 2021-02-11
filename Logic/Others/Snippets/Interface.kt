// デリゲートパターン
interface SomeCallback {
    fun didCallBack(number: Int)
}

class Callee {
    var callback: SomeCallback? = null

    fun execute(){
        callback?.didCallBack(1)
    }
}

// 呼び出し元
class Caller: SomeCallback {
    var callee = Callee()

    init {
        callee.callback = this
    }

    fun execute() {
        callee.execute()
    }
    override fun didCallBack(number: Int) {
        println("didCallBack!!!!")
    }
}

fun main() {

    val caller = Caller()
    caller.execute()



    // 直接CalleeにSomeCallbackの無名クラスを渡してこんなコールバックもできる
    val callee = Callee()
    callee.callback = object: SomeCallback {
        override fun didCallBack(number: Int) {
            println("didCallBack!!!! $number")
        }
    }
    callee.execute()

}