class QuickSort() {
 
    companion object {
        
        fun sort(list: MutableList<Int>): MutableList<Int> {
            
            QuickSort.quickSort(list, 0, list.count() - 1)
            return list
        }

        private fun quickSort(list: MutableList<Int>, leftIndex: Int, rightIndex: Int) {
        
            var leftCursor = leftIndex
            var rightCursor = rightIndex
            val middleValue = list[(leftCursor + rightCursor) / 2]
            
            do {
                while (list[leftCursor] < middleValue) {
                    leftCursor += 1
                }
                while (list[rightCursor] > middleValue) {
                    rightCursor -= 1
                }
                if (leftCursor <= rightCursor) {
                    QuickSort.swap(list, leftCursor, rightCursor)
                    leftCursor += 1
                    rightCursor -= 1
                }
            } while (leftCursor <= rightCursor)
            
            if (leftIndex < rightCursor) {
                QuickSort.quickSort(list, leftIndex, rightCursor)
            }
            if (leftCursor < rightIndex) {
                QuickSort.quickSort(list, leftCursor, rightIndex)
            }
        }
        
        private fun swap(list: MutableList<Int>, left: Int, right: Int) {
            
            val tempValue = list[left]
            list[left] = list[right]
            list[right] = tempValue
        }
    }
}

class QuickSortObject() {
    
    companion object {

        fun sort(list: MutableList<Hoge>): MutableList<Hoge> {
        
            QuickSortObject.quickSort(list, 0, list.count() - 1)
            
            return list
        }
        
        private fun quickSort(list: MutableList<Hoge>, leftIndex: Int, rightIndex: Int) {
            
            var leftCursor = leftIndex
            var rightCursor = rightIndex
            val middleOjbect = list[(leftCursor + rightCursor) / 2]
            
            do {
                while (list[leftCursor].number < middleOjbect.number) {
                    leftCursor += 1
                }
                while (list[rightCursor].number > middleOjbect.number) {
                    rightCursor -= 1
                }
                if (leftCursor <= rightCursor) {
                    QuickSortObject.swap(list, leftCursor, rightCursor)
                    leftCursor += 1
                    rightCursor -= 1
                }
            } while (leftCursor <= rightCursor)
            
            if (leftIndex < rightCursor) {
                QuickSortObject.quickSort(list, leftIndex, rightCursor)
            }
            if (leftCursor < rightIndex) {
                QuickSortObject.quickSort(list, leftCursor, rightIndex)
            }
        }
        
        private fun swap(list: MutableList<Hoge>, left: Int, right: Int) {
            
            val tempValue = list[left]
            list[left] = list[right]
            list[right] = tempValue
        }
    }
}

class Hoge(val number: Int)

fun main() {
    val list = mutableListOf(5, 3, 1, 4, 2)
    QuickSort.sort(list)
    println(list)

    val hogeList = mutableListOf(Hoge(5), Hoge(3), Hoge(1), Hoge(4), Hoge(2))
    QuickSortObject.sort(hogeList)
    for (hoge in hogeList) {
        println(hoge.number)
    }
}
