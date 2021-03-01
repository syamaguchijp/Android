// Nullable な変数の取り扱いのまとめ

class Sample() {

    fun methodA(h: String?) {
        var hoge = h // <- because h is read-only val

        // hoge = hoge.toUpperCase() // <- これはコンパイルエラーとなる
        // hoge = hoge!!.toUpperCase() // <- nullの時にクラッシュする（強制アンラップ）。推奨はされない
        hoge = hoge?.toUpperCase() // <- これだとnullの時でもクラッシュせず何も起こらない（セーフコール）

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

        hoge?.let {
            println("hoge is not null.")
        }
    }

    fun methodB(fuga: String?) {
        // if-let文
        fuga?.also { fuga2 -> // 「fuga2 ->」を記述しない場合は、このブロック内はfugaは「it」で受ける
            // fuga2 = fuga2.toLowerCase() <- 再代入はできないので、利便性低し
            println(fuga2.toLowerCase())
        } ?: run {
            println("fuga is null")
        }

        // guard文（エルビス演算子）
        var fuga3 = fuga ?: return
        fuga3 = fuga3.toUpperCase() // ?や!!をつけずにhogeにアクセスできるし、if文が深くならないので便利
        println(fuga3)

        // エルビス演算子
        //var fuga4 = fuga ?: "Elvis"
        //fuga4 = fuga4.toUpperCase()
    }
}

fun main() {

    Sample().methodA(null)
    println("------------")
    Sample().methodB(null)
    println("------------")

    Sample().methodA("ABC")
    println("------------")
    Sample().methodB("XYZ")
    println("------------")
}