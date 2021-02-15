// open修飾子で継承可能なクラスとなる
open class User constructor(var name: String, var age: Int = 100) { // プライマリコンストラクタ

    // プライマリコンストラクタにおいてvarでnameやageを定義しているので以下メンバ宣言は要らない
    //var name: String
    //var age: Int
    var isMale: Boolean = false

    // セカンダリコンストラクタ 1
    constructor(name: String, isMale: Boolean): this(name, 20) {// <-プライマリコンストラクタを呼び出さなければならない
        this.isMale = isMale
    }

    // セカンダリコンストラクタ 2
    constructor(): this("Sato", true) // セカンダリコンストラクタ 1を呼び出す

    // 初期化ブロック（プライマリコンストラクタからコールされる）
    init {
        // プライマリコンストラクタにおいてvarでnameやageを定義しているので以下記述は要らない
        //this.name = name
        //this.age = age
    }

    // open修飾子でオーバーライドを許容
    open fun dump() {
        println("name=${name} age=${age} isMale=${isMale} region=${region}")
    }

    // プロパティのアクセッサ（セッター）
    var region = "Tokyo"
        set(value) {
            if (value.length < 3) {
                throw IllegalArgumentException("文字数は3文字以上で入力してください")
            }
            field = value
        }
}

// Userクラスを継承
class SpecialUser(name: String, age: Int): User(name, age) {

    // メソッドのオーバーライド
    override fun dump() {
        println("名前=${name} 年齢=${age} 地域=${region}")
    }
}


fun main() {
    val user = User("Takeda", 21)
    user.dump()

    val user2 = User("Yamada", true)
    user2.dump()

    val user3 = User()
    //user3.region = "aa"
    user3.region = "Osaka"
    user3.dump()

    val specialUser = SpecialUser("Takagi", 19)
    specialUser.dump()
}