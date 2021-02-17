// Nullable な変数の取り扱いのまとめ

fun main() {

    var hoge: String? = null
    // hoge = hoge.toUpperCase() // <- これはコンパイルエラーとなる
    // hoge = hoge!!.toUpperCase() // <- nullの時にクラッシュする（強制アンラップ）。推奨はされない
    hoge = hoge?.toUpperCase() // <- これだとnullの時でもクラッシュせず何も起こらない（セーフコール）

    //hoge = "hoge"

    // if文でのnullチェック
    if (hoge != null) {
        // このブロック内では、?や!!をつけずにhogeにアクセスできる
        hoge = hoge.toUpperCase()
        println(hoge)
    } else {
        println("hoge is null.")
    }

    if (hoge == null) {
        println("hoge is null.")
    } else {
        // このブロック内では、?や!!をつけずにhogeにアクセスできる
        hoge = hoge.toUpperCase()
        println(hoge)
    }

    println("------------")

    // if-let文
    var fuga: String? = null //"FUGA"
    fuga?.also { fuga2 -> // 「fuga2 ->」を記述しない場合は、このブロック内はfugaは「it」で受ける
        // fuga2 = fuga2.toLowerCase() <- 再代入はできないので、利便性低し
        println(fuga2.toLowerCase())
    } ?: run {
        println("fuga is null")
    }

    println("------------")

    // エルビス演算子
    var name: String? = null
    var name2 = name ?: "Elvis"
    name2 = name2.toUpperCase()
    println(name2)

    // guard文（エルビス演算子）
    var str: String? = null //"str"
    var str2 = str ?: return
    str2 = str2.toUpperCase() // ?や!!をつけずにhogeにアクセスできるし、if文が深くならないので便利
    println(str2)


}