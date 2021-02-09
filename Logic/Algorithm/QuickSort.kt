class QuickSort() {
 
    companion object {
        
        fun sort(array: MutableList<Int>): MutableList<Int> {
            
            QuickSort.quickSort(array, 0, array.count() - 1)
            return array
        }

        private fun quickSort(array: MutableList<Int>, leftIndex: Int, rightIndex: Int) {
        
            var leftCursor = leftIndex
            var rightCursor = rightIndex
            val middleValue = array[(leftCursor + rightCursor) / 2]
            
            do {
                while (array[leftCursor] < middleValue) {
                    leftCursor += 1
                }
                while (array[rightCursor] > middleValue) {
                    rightCursor -= 1
                }
                if (leftCursor <= rightCursor) {
                    QuickSort.swap(array, leftCursor, rightCursor)
                    leftCursor += 1
                    rightCursor -= 1
                }
            } while (leftCursor <= rightCursor)
            
            if (leftIndex < rightCursor) {
                QuickSort.quickSort(array, leftIndex, rightCursor)
            }
            if (leftCursor < rightIndex) {
                QuickSort.quickSort(array, leftCursor, rightIndex)
            }
        }
        
        private fun swap(array: MutableList<Int>, left: Int, right: Int) {
            
            val tempValue = array[left]
            array[left] = array[right]
            array[right] = tempValue
        }
    }
}

class QuickSortObject() {
    
    companion object {

        fun sort(array: MutableList<Hoge>): MutableList<Hoge> {
        
            QuickSortObject.quickSort(array, 0, array.count() - 1)
            
            return array
        }
        
        private fun quickSort(array: MutableList<Hoge>, leftIndex: Int, rightIndex: Int) {
            
            var leftCursor = leftIndex
            var rightCursor = rightIndex
            val middleOjbect = array[(leftCursor + rightCursor) / 2]
            
            do {
                while (array[leftCursor].number < middleOjbect.number) {
                    leftCursor += 1
                }
                while (array[rightCursor].number > middleOjbect.number) {
                    rightCursor -= 1
                }
                if (leftCursor <= rightCursor) {
                    QuickSortObject.swap(array, leftCursor, rightCursor)
                    leftCursor += 1
                    rightCursor -= 1
                }
            } while (leftCursor <= rightCursor)
            
            if (leftIndex < rightCursor) {
                QuickSortObject.quickSort(array, leftIndex, rightCursor)
            }
            if (leftCursor < rightIndex) {
                QuickSortObject.quickSort(array, leftCursor, rightIndex)
            }
        }
        
        private fun swap(array: MutableList<Hoge>, left: Int, right: Int) {
            
            val tempValue = array[left]
            array[left] = array[right]
            array[right] = tempValue
        }
    }
}

class Hoge(val number: Int)

fun main() {
    val array = mutableListOf(5, 3, 1, 4, 2)
    QuickSort.sort(array)
    println(array)

    val hogeList = mutableListOf(Hoge(5), Hoge(3), Hoge(1), Hoge(4), Hoge(2))
    QuickSortObject.sort(hogeList)
    for (hoge in hogeList) {
        println(hoge.number)
    }
}
