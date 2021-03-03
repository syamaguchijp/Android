
interface User {
    var appendix: String
    fun payBill(payment: Int): Int
}

class NormalUser(): User {
    override var appendix = "通常会員"
    override fun payBill(payment: Int): Int {
        return (payment * 1.10).toInt()
    }
}

class SpecialUser: User {
    override var appendix = "特別会員"
    override fun payBill(payment: Int): Int {
        return (payment * 0.90).toInt()
    }
}

// 生成するオブジェクトを条件に応じて切り替える
class UserFactory {
    companion object {
        fun createUser(age: Int): User {
            if (age >= 50) {
                return SpecialUser()
            }
            return NormalUser()
        }
    }
}

fun main() {
    val user1 = UserFactory.createUser(49)
    println("${user1.appendix}")
    val user2 = UserFactory.createUser(50)
    println("${user2.appendix}")
}