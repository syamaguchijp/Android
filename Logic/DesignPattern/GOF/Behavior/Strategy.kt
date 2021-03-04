// interfaceを実装したクラスを使い分け、アルゴリズムの切り替えを行う

interface CarStrategy {
    fun runMax()
}

class SkylineStrategy: CarStrategy {
    override fun runMax() {
        println("時速130kmで走る")
    }
}

class NBoxStrategy: CarStrategy {
    override fun runMax() {
        println("時速100kmで走る")
    }
}

class CarDriver(val strategy: CarStrategy) {
    fun drive() {
        strategy.runMax()
    }
}

fun main() {
    val driver = CarDriver(SkylineStrategy())
    driver.drive()
}