import java.util.Date

// オブジェクトの状態を保存するパターン
// 状態の復元などに役立つ
class User(var name: String, var currentScore: Int) {
    // メメントに保存
    fun save(): UserMemento {
        return UserMemento(currentScore)
    }
    // 復元
    fun restore(memento: UserMemento) {
        currentScore = memento.score
        println("${memento.score}に戻しました")
    }
}

// Userの状態を保持するメメント
class UserMemento(var score: Int) {
    var date: Date
    init {
        date = Date()
    }
}

fun main() {
    val user1 = User("Yamada", 90)
    val memento = user1.save() // 値の保存
    user1.currentScore = 50 // 値の変更
    user1.restore(memento) // 値の復元
}