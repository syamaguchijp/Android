// 抽象クラス
abstract class AbstractComputer() {
    abstract fun add()
    abstract fun multiply()
    fun compute() {
        add()
        multiply()
    }
}

// 具象クラス
// 抽象クラスで定義された add と multiply の内容を実装する
class ConcreteComputer(): AbstractComputer() {
    override fun add() {
        println("1 + 1 = 2")
    }
    override fun multiply() {
        println("10 * 10 = 100")
    }
}

fun main() {
    val computer = ConcreteComputer()
    computer.compute()
}