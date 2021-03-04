class User() {
    var name = "AAAA"
    fun dumpName() {
        println("name = ${name}")
    }
}

// 既存クラス（User）を継承したり変更を加えたりすることなく、Userクラスの拡張を行う
class UserDecolator() {
    val user = User()
    fun dumpName() {
        println("name = ${user.name}様")
    }
}

fun main() {
    User().dumpName()
    UserDecolator().dumpName()
}