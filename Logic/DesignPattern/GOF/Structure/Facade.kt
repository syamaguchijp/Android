// 窓口を作る。ストレージなどの読み書きに利用
class UserDafaultManager() {
    
    private val ud = UserDefaults.standard
    private val nameKey = "name"
    
    var name: String?
        get() = ud.string(nameKey)
        set(value) {
            ud.set(value, nameKey)
        }
}

fun main() {
    val ud = UserDafaultManager()
    ud.name = "hoge"
    println("${ud.name}")
}