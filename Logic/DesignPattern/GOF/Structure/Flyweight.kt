// リソースを節約して軽量化するため、インスタンスを無駄に生成しないパターン

class Car(val color: String) {
    fun run() {
        println("run")
    }
}

class CarFactory() {
    private var cars = mutableMapOf<String, Car>()
    fun getCar(color: String): Car {
        val c = cars[color]
        if (c != null) {
            // すでに存在する場合はインスタンスを生成せずに再利用する
            return c
        }
        val car = Car(color)
        cars[color] = car
        return car
    }
}

fun main() {
    val carFactory = CarFactory()
    val car = carFactory.getCar("RED")
    car.run()
}