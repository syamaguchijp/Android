// 普段は代理人Proxyが働き、Proxyではできない内容の動作においては本人が登場する

interface User {
    fun doNormalWork()
    fun doHardWork()
}

// 本人
class Person(): User {
    override fun doNormalWork() {
        println("Person doNormalWork")
    }
    override fun doHardWork() {
        println("Person doHardWork")
    }
}

// 代理人（Proxy）
class PersonProxy(): User {
    private var person: Person? = null
    override fun doNormalWork() {
        println("PersonProxy doNormalWork")
    }
    override fun doHardWork() {
        // ハードな仕事は本人でないと出来ないためここで本人を呼ぶ
        person?.let {
            it.doHardWork()
            return
        }
        person = Person()
        person?.doHardWork()
    }
}

fun main() {
    val proxy = PersonProxy()
    proxy.doNormalWork()
    proxy.doHardWork()
}
