// 先入れ先出し（FIFO）を実現するキュー。リングバッファ
class IntQueue(var max: Int) {

    var frontIndex = 0 // 先頭要素のインデックス
    var rearIndex = 0 // 末尾要素のインデックス
    var num = 0 // キューに蓄えられているデータ数
    var queue: Array<Int?>

    init {
        queue = arrayOfNulls(max)
    }

    fun enqueue(x: Int) {

        if (num >= max) {
            throw OverFlowQueueException()
        } else {
            println("queue ${x} to ${rearIndex}")
            queue[rearIndex++] = x
            num++
            if (rearIndex == max) {
                // リングバッファのため、一回転してrearIndexは配列の先頭へ。
                rearIndex = 0
            }
        }
    }

    fun dequeue(): Int? {

        if (num <= 0) {
            throw EmptyQueueException()
        }
        val x = queue[frontIndex++]
        num--
        if (frontIndex == max) {
            // リングバッファのため、一回転してfrontIndexは配列の先頭へ。
            frontIndex = 0
        }
        println("dequeue ${x}")
        return x
    }

    class EmptyQueueException: RuntimeException () {
    }
    class OverFlowQueueException: RuntimeException () {
    }
}

fun main() {
    val queue = IntQueue(3)
    try {
        queue.enqueue(10)
        queue.enqueue(20)
        queue.enqueue(30)
        queue.dequeue()
        queue.dequeue()
        queue.dequeue()
    } catch(e: RuntimeException) {
        println(e)
    }
}