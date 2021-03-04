// 容器と中身を同一視し、再帰的な構造を作る
// 下記例では、Employee型とEmployee型を要素に構成される集合体(Company)も同じものとして扱えるようにする
interface Member {
    var name: String
    var score: Int
    fun dumpScore()
}

class Employee(override var name: String, override var score: Int): Member {
    override fun dumpScore() {
        println("${name} => ${score}")
    }
}

class Company(override var name: String, var employees: Array<Employee>): Member {
    override var score: Int = 0
        get() {
            var sum = 0
            for (emp in employees) {
                sum += emp.score
            }
            return sum
        }
    override fun dumpScore() {
        print("${name} => ${score}")
    }
}

fun main() {
    val employee1 = Employee("aaaa", 1)
    val employee2 = Employee("bbbb", 2)
    val employee3 = Employee("cccc", 3)

    val compamy = Company("XXXX inc,", arrayOf(employee1, employee2, employee3))

    // 以下のように従業員も会社も同じように扱える
    employee1.dumpScore()
    employee2.dumpScore()
    employee3.dumpScore()
    compamy.dumpScore()
}