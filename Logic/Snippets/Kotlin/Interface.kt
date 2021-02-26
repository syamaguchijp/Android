import java.lang.ref.WeakReference;

// デリゲートパターン
interface SomeCallback {
    fun didCallBack(number: Int)
}

class Callee {
    var callbackRef: WeakReference<SomeCallback>? = null // 弱参照

    fun execute(){
        val callback = callbackRef?.get()
        callback?.didCallBack(1)
    }
}

// 呼び出し元
class Caller: SomeCallback {
    var callee = Callee()

    init {
        callee.callbackRef =  WeakReference<SomeCallback>(this)
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
    callee.callbackRef = WeakReference<SomeCallback>(object: SomeCallback {
        override fun didCallBack(number: Int) {
            println("didCallBack!!!! $number")
        }
    })
    callee.execute()
}