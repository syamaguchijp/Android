class OverFlowValueException: RuntimeException () {
}

class Test() {
    fun test(x: Int): Int {
        if (x >= 10000) {
            throw OverFlowValueException()
        }
        return 777
    }
}

fun main() {
    val ans = try { // 関数の戻り値を受けたい場合
        Test().test(10000) 
    } catch(e: RuntimeException) {
        println(e)
        when(e){
            is OverFlowValueException -> println("OverFlowValueException!!!")
            else -> println("else")
        }
        null // 関数の戻り値としてnullを返却
    } finally {
        println("finally")
    }
    
    println(ans)
}
