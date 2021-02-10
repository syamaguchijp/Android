class BinarySearch() {
 
    companion object {
        /**
        * 二分探索を実行する
        *
        * @param searchNumber 探索する数値
        * @param array 昇順にソートされていることが前提
        * @return 配列に存在しない場合はnullを返却する。存在する場合は配列の該当indexを返却する。
        */
        fun search(searchNumber: Int, array: MutableList<Int>): Int? {
            
            var minIndex = 0
            var maxIndex =  array.count() - 1
            
            while (minIndex <= maxIndex) {
                val midIndex = (minIndex + maxIndex) / 2
                if (array[midIndex] == searchNumber) {
                    return midIndex
                } else if (array[midIndex] > searchNumber) {
                    maxIndex = midIndex - 1
                } else {
                    minIndex = midIndex + 1
                }
            }
            
            return null
        }
    }
}

fun main() {
    val list = (0..99).toMutableList()
    val answer1 = BinarySearch.search(99, list)
    if (answer1 != null) {
        print(answer1)
    }
}
