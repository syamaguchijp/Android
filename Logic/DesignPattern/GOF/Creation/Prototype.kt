
// 時間のかかる処理などを行った後の値を引き継いで、そのオブジェクトのコピーを作りたい場合などに用いる
class User(val name: String) {
    
    var number = 0

    // 時間のかかる処理
    fun addNumber() {
        number = 0
        for (i in 1..100000) {
            number += 1
        }
        println("${number}")
    }

    // 自分自身の既存値をセットし、新しいインスタンスを生成する
    fun clone(): User {
        val cloneUser = User(name)
        cloneUser.number = number
        return cloneUser
    }
}

fun main() {
    val user1 = User("ym")
    user1.addNumber()

    val user2 = user1.clone()
}