// 最短経路アルゴリズム　ダイクストラ法
class Dijkstra() {

    /**
     出発地点から到着地点の距離を求める
     * @args mapArray 地点マップ用多次元配列（各ノードの距離関係）「mapArray[fromNode][toNode] = 距離」
     * @args nodeNum 全ノード数
     * @args startNode 出発地点ノード
     * @args endNode 到着地点ノード
     */
     fun execute(mapArray: Array<Array<Int?>>, nodeNum: Int, startNode: Int, endNode: Int) {

        // 初期化
        var resultArray = Array<Result>(nodeNum){Result()}
        resultArray[startNode].distance = 0 // 出発地点までの距離は0

        while (true) {
            // 未確定の中で最も近いノードを求める
            val nearestIndex = findNearestNode(resultArray)
            if (nearestIndex < 0) {
                break // 全ノードが確定した場合
            }
            if (resultArray[nearestIndex].distance == Int.MAX_VALUE) {
                break // 非連結グラフのためbreak
            }
            resultArray[nearestIndex].isFixed = true // 当該ノードまでの最短距離が確定となる
            // 「mapArray[fromNode][toNode] = 距離」であるため、配列を走査して隣のNodeとの距離を調べる
            for (i in 0..nodeNum-1) {
                mapArray[nearestIndex][i]?.let{
                    if (!resultArray[i].isFixed) { // 距離がまだ未確定であればその距離を求めて記録する
                        val minDistance = resultArray[nearestIndex].distance + mapArray[nearestIndex][i]!!
                        // 今までの距離よりも小さいものであれば記録
                        if (minDistance < resultArray[i].distance) {
                            resultArray[i].distance = minDistance
                        }
                    }
                }
            }
        }
        // 結果を出力する
        if (resultArray[endNode].distance == Integer.MAX_VALUE) {
            println("FAIL")
        } else {
            println("Distance is ${resultArray[endNode].distance}");
        }
    }

    // まだ距離が確定していないNodeの中で、最も近いものを探す
    private fun findNearestNode(resultArray: Array<Result>): Int {

        var ans = 0
        for (i in 0..resultArray.size-1) {
            if (!resultArray[ans].isFixed) {
                // 未確定のNode
                break
            }
            ans++
        }
        if (ans == resultArray.size) {
            // 未確定のNodeが存在しないため終了
            return -1
        }
        // 距離が短いものを探す
        for (i in ans+1..resultArray.size-1) {
            if (!resultArray[i].isFixed && resultArray[i].distance < resultArray[ans].distance) {
                ans = i
            }
        }
        return ans
    }
}

data class Result(var distance: Int = Int.MAX_VALUE, var isFixed: Boolean = false) {
    // isFixed = 最短距離が確定したかどうか
}

fun main() {
    
    val nodeNum = 7 // 全ノード数
    val startNode = 0 // 出発地点
    val endNode = 6 // 到着地点

    // 地点マップ用多次元配列「mapArray[fromNode][toNode] = 距離」
    val mapArray = Array(nodeNum) { arrayOfNulls<Int?>(nodeNum) }
    mapArray[0][1] = 72
    mapArray[0][3] = 24
    mapArray[0][2] = 36
    mapArray[1][3] = 60
    mapArray[1][4] = 145
    mapArray[2][3] = 95
    mapArray[2][5] = 49
    mapArray[3][6] = 81
    mapArray[4][6] = 50
    mapArray[5][6] = 70

    Dijkstra().execute(mapArray, nodeNum, startNode, endNode)
}