import java.util.Date

interface Builder {
    fun setName(name: String)
    fun setBirthday(birthday: Date)
    fun setAge(age: Int)
}

class UserBuilder: Builder {
    val user = User()
    override fun setName(name: String) {
        user.name = name
    }
    override fun setBirthday(birthday: Date) {
        user.birthday = birthday
    }
    override fun setAge(age: Int) {
        user.age = age
    }
}

class User() {
    var name: String = ""
    var birthday: Date = Date()
    var age: Int = 0
    fun dump() {
        println("name=${name} age=${age} birthday=${birthday}")
    }
}

// Directorを通じて、Builderがオブジェクトを段階的に組み上げていくパターン
class Director(val builder: Builder) {
    fun build() {
        builder.setName("Takeda")
        builder.setBirthday(Date())
        builder.setAge(20)
    }
}

fun main() {
    val builder = UserBuilder()
    val director = Director(builder)
    director.build()
    builder.user.dump()
}