class RoundRobin() {
 
    companion object {
        /**
        * 総当りを作成する
        */
        fun makeRoundRobin(seedList: List<Int>): MutableList<List<Int>> {
            
            val totalIndex = seedList.count()
            var pow = totalIndex
            for (i in 1..totalIndex-1) {
                pow = pow * totalIndex
            }
            
            var answerList: MutableList<List<Int>> = mutableListOf()
            
            var initialRow: MutableList<Int> = mutableListOf()
            for (i in 0..totalIndex-1) {
                initialRow.add(0)
            }
            
            for (i in 0..pow-1) {
                val newArray = RoundRobin.makeArray(i, initialRow)
                if (newArray == null) {
                    continue
                }
                answerList.add(newArray)
            }
            return answerList
        }
        
        fun makeArray(currentRowNum: Int, lastRow: List<Int>): MutableList<Int>? {
        
            val totalIndexs = lastRow.count()
            var newRow: MutableList<Int> =  mutableListOf()
            for (i in 0..totalIndexs-1) {
                newRow.add(0)
            }
            
            var shou = currentRowNum
            var amari = currentRowNum
            for (i in 0..totalIndexs-1) {
                amari = shou % totalIndexs
                shou = shou / totalIndexs
                newRow[totalIndexs - i - 1] = amari
                newRow[totalIndexs - i - 2] = shou
                if (shou < totalIndexs) {
                    break
                }
            }
            
            var pow = totalIndexs
            for (i in 1..totalIndexs-1) {
                pow = pow * totalIndexs
            }
            
            if (currentRowNum < pow && !RoundRobin.hasSameValue(newRow)) {
                return newRow
            }
            
            return null
        }

        fun hasSameValue(list: List<Int>): Boolean {
            
            for (i in list.withIndex()) {
                val index = list.indexOf(list[i.index])
                if (index >= 0 && index != i.index) {
                    return true
                }
            }
            return false
        }
    }
}

fun main() {
    val answer = RoundRobin.makeRoundRobin(listOf(0, 1, 2, 3))
    println(answer)
}
