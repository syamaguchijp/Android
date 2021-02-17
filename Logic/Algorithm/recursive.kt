// 再帰的アルゴリズム。ユークリッド互除法とハノイの塔

// ユークリッド互除法（最大公約数）
class Euclid() {
    fun mutiualDivision(x: Int, y: Int): Int {
        if (y == 0) {
            return x
        }
        return mutiualDivision(y, x%y)
    }
}

// ハノイの塔
class TowerOfHanoi() {
    // x軸からy軸へ円盤numを移動する
    fun move(num: Int, x: Int, y: Int) {
        if (num > 1) {
            move(num-1, x, 6-x-y)
        }
        println("${x}軸から${y}軸へ円盤${num}を移動した。")
        if (num > 1) {
            move(num-1, 6-x-y, y)
        }
    }
}

fun main() {

    println(Euclid().mutiualDivision(21, 12))
    
    TowerOfHanoi().move(3, 1, 3)
}
