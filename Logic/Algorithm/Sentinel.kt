// 線形探索　番兵法
class LinearSearch() {

    // listからkeyと一致する要素を線形探索して、その要素番号を返却する
    fun search(list: MutableList<Int>, key: Int): Int {

        var index = 0
        
        list.add(key) // 末尾に番兵を追加する

        while (true) {
            if (list[index] == key) {
                break
            }
            index++
        }
        if (index == list.count()) {
            return -1
        }
        return index
        
        /* 
        // 番兵法を使わない場合
        while (true) {
            if (index == list.count()) { // このif文のコスト
                // 探索失敗のため-1を返却
                return -1
            } 
            if (list[index] == key) {
                return index
            }
            index++
        }*/
    }
}

fun main() {
    println(LinearSearch().search(mutableListOf(0, 10, 5, 3, 8, 9, 7), 3))
    
}