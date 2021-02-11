data class Video(var title: String = "") {

    var year: Int = 1995

    // データクラスを定義すれば、自動的に以下のメソッドが具備される
    // * equals（プライマリコンストラクタで宣言されたプロパティのみで比較される）
    // * toString
    // * componentN
    // * copy

    // 継承はできない
}

fun main() {
    val video1 = Video("Takeda")
    video1.year = 2001
    val video2 = Video("Aoyama")
    video2.year = 2015
    println(video2)
}