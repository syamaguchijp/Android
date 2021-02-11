fun main() {
    
    //region 配列

    // [0, 1, 2]
    arrayOf(0, 1, 2)

    // [0, 1, 2, 3, 4]
    Array(5){it} // Arrayコンストラクタ 要素数とラムダ式

    // プリミティブ型配列（IntArray, LongArray, ByteArray .etc） 
    intArrayOf(0, 1, 2)
    
    // 空の配列（後で値を入れる場合）[null,null,null]
    val array: Array<Int?> = arrayOfNulls(3)
    array[0] = 10
    array[1] = 11
    array[2] = 12

    // 配列の要素数
    println(array.size)

    // 配列の走査
    val strAry = arrayOf("Tokyo", "Osaka", "Hakata")
    for (i in 0..strAry.size-1) {
        println(strAry[i])
    }
    for (index in strAry.indices) {
        println(strAry[index])
    }
    for (element in strAry) {
        println(element)
    }
    strAry.forEach { element ->
        println(element)
    }

    // 存在確認
    if ("Hakata" in strAry) {
        println("contains Hakata")
    }

    //endregion

    //region リスト（ミュータブル）
    
    var mlist = mutableListOf<String>() // 空のリストの生成

    mlist = mutableListOf("Tokyo", "Osaka")

    mlist.add("Nagoya") // 要素の追加
    mlist.addAll(listOf("Sendai", "Sapporo"))

    // リストの要素数
    println(mlist.count())

    // リストの走査
    for (i in 0..mlist.count()-1) {
        println(mlist[i])
    }
    for (index in mlist.indices) {
        println(mlist[index])
    }
    for (element in mlist) {
        println(element)
    }
    mlist.forEach { element ->
        println(element)
    }
    mlist.forEachIndexed { index, element -> 
        println("$index $element")
    }

    // 存在確認
    if ("Sapporo" in mlist) {
        println("contains Sapporo")
    }

    // 要素の削除
    mlist.remove("Nagoya")
    mlist.removeAt(0)
    mlist.removeIf { it.contains("S") } // 条件指定削除（ラムダ式）
    mlist.clear() // 要素の全削除

    // immutableへの変換
    val immutableList: List<String> = mlist.toList()
    // mutableへの変換
    val mutableList: MutableList<String> = immutableList.toMutableList()

    //endregion

    //region セット型

    val set: MutableSet<Int> = mutableSetOf<Int>(1, 2, 3, 4)
    set.add(1)
    set.add(5)
    
    // セットの要素数
    println(set.count())

    // セットの走査
    for (element in set) {
        println(element)
    }
    set.forEach { element ->
        println(element)
    }

    // 存在確認
    if (set.contains(5)) {
        print("contains 5")
    }

    // セットの要素の削除
    set.remove(2)
    set.clear()

    // immutableへの変換
    val immutableSet: Set<Int> = set.toSet()
    // mutableへの変換
    val mutableSet: MutableSet<Int> = immutableSet.toMutableSet()

    //endregion

    //region 辞書

    val map: MutableMap<String, Int> = mutableMapOf<String, Int>("a" to 1, "b" to 2, "c" to 3)
    map["a"] = 777 // 要素の変更
    map["d"] = 4 // 要素の追加

    // 要素数
    println(map.count())

    // mapの走査
    for ((key, value) in map) {
        println("$key = $value")
    }

    // 存在確認
    if (map.contains("a")) {
        print("contains a")
    }

    // 要素の削除
    map.remove("a")
    map.clear()

    // immutableへの変換
    val immutableMap: Map<String, Int> = map.toMap()
    // mutableへの変換
    val mutableMap: MutableMap<String, Int> = immutableMap.toMutableMap()

    //endregion

}