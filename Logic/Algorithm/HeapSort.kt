// ヒープの作成とヒープソート
// Heap = 完全二分木（親の値 >= 子の値）

class HeapSort() {

    // 配列をヒープ化する
    fun makeHeap(array: Array<Int>, heapSize: Int, root: Int) {

        var largest = root
        var left = 2 * root + 1
        var right = 2 * root + 2
 
        // 左の子が根より大きい時
        if (left < heapSize && array[left] > array[largest]) {
            largest = left
        }
 
        // 右の子がlargetsより大きい時
        if (right < heapSize && array[right] > array[largest]) {
            largest = right
        }

        if (largest != root) {
            val temp = array[root]
            array[root] = array[largest]
            array[largest] = temp
 
            // 再帰的に実行
            makeHeap(array, heapSize, largest)
        }
    }

    // 配列に対してヒープソートを実行する
    fun heapSort(array: Array<Int>) {
 
        for (i in array.size / 2 - 1 downTo 0) {
            makeHeap(array, array.size, i)
        }

        for (i in array.size - 1 downTo 0) {
            val temp = array[0]
            array[0] = array[i]
            array[i] = temp
 
            makeHeap(array, i, 0)
        }
    }
}

fun main() {

    var array = arrayOf(20, 1, 11, 41, 200, 69, 81, 30)
    HeapSort().heapSort(array)
    
    for (i in array) {
        println(i)
    }
}