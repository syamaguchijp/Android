fun main() {

    val str = "ABCDEFG12345"

    // 文字列内の変数利用
    println("str = ${str}")

    // 一致判定
    val strA = "ABC";
    println(strA.equals("ABC"));
    println(strA.equals("aBc", ignoreCase = true));

    // 長さ
    println(str.length)

    // 切り出し
    println(str.substring(1)) // BCDEFG12345
    println(str.substring(1, 3)) // BC

    // 検索
    println(str.contains("ABC")) // true
    println(str.indexOf("CDE")) // 2
    println(str.startsWith("ABC")) // true
    println(str.endsWith  ("45")) // true

    // 分割
    val list = "A,B,C,D".split(",")
    list.forEach{
        println(it)
    }

    // 結合
    println("Honda" + "Kawasaki" + 400)

    var list2 = 1..5
    var s = list2.joinToString(",")
    println(s) // 1,2,3,4,5

    // 大文字小文字
    println("abc".toUpperCase()) // ABC
    println("ABC".toLowerCase()) // abc
    // 先頭の文字列のみ
    println("abc".capitalize()) // Abc
    println("Abc".decapitalize()) // abc

    // 置換
    println(str.replace("AB", "XXX"))

    // 削除
    println("abcd".drop(2)) // cd
    println("abcd".dropLast(2)) // ab
    println("abcd".removePrefix("ab")) // cd
    println("abcd".removeSuffix("d")) // abc

    // 前後の空白を削除
    var str2 = " ab cd "
    println(str2.trim()) // ab cd
    println(str2.trimStart()) // ab cd 
    println(str2.trimEnd()) //  ab cd
    
    // フォーマット
    println("%05d".format(123)) // 00123
    println("%5d".format(123)) //   123
    println("%d %s".format(123, "abc")) // 123 abc

    // 数値に変換
    val numStr = "123"
    println(Integer.parseInt(numStr))
    
}