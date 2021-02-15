// 後入れ先出し（LIFO）を実現するスタック
class IntStack(var max: Int) {

    var pointer = 0
    var stack: Array<Int?>

    init {
        stack = arrayOfNulls(max)
    }

    // スタックに値をプッシュする
    fun push(x: Int) {
        if (pointer >= max) {
            throw OverFlowStackException()
        } else {
            println("push ${x} to ${pointer}")
            stack[pointer++] = x   
        }
    }

    // スタックからデータをポップする
    fun pop(): Int? {
        if (pointer <= 0) {
            throw EmptyStackException()
        } else {
            println("pop ${stack[pointer-1]}")
            return stack[--pointer]
        }
    }

    class EmptyStackException: RuntimeException () {
    }
    class OverFlowStackException: RuntimeException () {
    }
}

fun main() {
    val stack = IntStack(3)
    try {
        stack.push(10)
        stack.push(20)
        stack.push(30)
        stack.pop()
        stack.pop()
        stack.pop()  
    } catch(e: RuntimeException) {
        println(e)
    }
}