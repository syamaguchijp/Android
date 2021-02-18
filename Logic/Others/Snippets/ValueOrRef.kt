
// 値型と参照型のメモ

class Person(var name: String = "", var age: Int = 0) {
}
data class Robot(var name: String = "", var age: Int = 0) {
}

class Test() {
    
    // 値型
    
    // 値型の値渡し（String）
    fun method7(str: String): String {
        //str = "XXX" //<- コンパイルエラー
        var ans = str // 値型のためcopyされて別物となる
        ans = "XXX"
        return ans
    }
    
    // 参照型
    
    // 参照型の値渡し（Array）
    fun method3(array: Array<String>): Array<String> {
        array[1] = "XXXX" //<- コンパイルエラーにならない
        var ans = array // 参照
        ans[1] = "ZZZZ"
        return ans
    }

    // 参照型の値渡し（Dictionary）
    fun method5(dict: MutableMap<String, String>): MutableMap<String, String> {
        dict["b"] = "XXXX" //<- コンパイルエラーにならない
        var ans: MutableMap<String, String> = dict // 参照
        ans["b"] = "ZZZZ"
        return ans
    }

    // 参照型の値渡し
    fun method10(person: Person) {
        person.name = "Yamada"
    }

    // Data Class
    fun method11(robot: Robot): Robot {
        robot.name = "Yamada" //<- コンパイルエラーにならない
        var ans = robot // 参照
        ans.name = "Takeda"
        return ans
    }
}

fun main() {
    val test = Test()

    // 値型

    var myStr = "Yamaha"
    val myStr2 = test.method7(myStr)
    println("myStr=${myStr}") // 引数で渡したものは変わっていない
    println("myStr2=${myStr2}")

    // 参照型

    var myArray = arrayOf("AAA", "BBB", "CCC")
    val myArray2 = test.method3(myArray)
    println("myArray[1]=${myArray[1]}") // myArray2と同じになる
    println("myArray2[1]=${myArray2[1]}")

    var myDict = mutableMapOf<String, String>("a" to "AAA", "b" to "BBB", "c" to "CCC")
    val myDict2 = test.method5(myDict)
    println("myDict[b]=${myDict["b"]}") // myDict2と同じになる
    println("myDict2[b]=${myDict2["b"]}")

    val person = Person()
    test.method10(person)
    println("person.name=${person.name}")

    val person3 = person // コピーされずに同じものの参照となる
    person3.name = "Person3"
    println("person.name=${person.name}") // こちらも"Person3"となる
    println("person3.name=${person3.name}")

    var robot = Robot()
    robot.name = "Honda"
    var robot2 = test.method11(robot)
    println("robot.name=${robot.name}") // 引数で渡したものも変わっている
    println("robot2.name=${robot2.name}")

    var robot4 = robot2 // 参照
    robot4.name = "Kawasaki"
    println("robot2.name=${robot2.name}") // robot4と同じになる
    println("robot4.name=${robot4.name}")
}

