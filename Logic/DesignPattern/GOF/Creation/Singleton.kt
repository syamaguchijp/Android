class SingletonTest {

    companion object {
        private var instance: SingletonTest? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: SingletonTest().also { instance = it }
        }
    }
}

fun main() {
    val s1 = SingletonTest.getInstance()
    val s2 = SingletonTest.getInstance()
    if (s1 === s2) {
        println("Singleton!")
    }
}