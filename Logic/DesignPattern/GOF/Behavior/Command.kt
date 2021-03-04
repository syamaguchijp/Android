
interface DrawCommand {
    fun draw()
}

class DrawRect: DrawCommand {
    override fun draw() {
        println("四角形を書く")
    }
}

class DrawRound: DrawCommand {
    override fun draw() {
        println("円を書く")
    }
}

// 命令を呼び出す方法を集約。
// 出した命令の履歴を把握することなどに役立つ
class DrawManager {
    val drawRect = DrawRect()
    val drawRound = DrawRound()
    
    fun draw1() {
        drawRect.draw()
    }
    fun draw2() {
        drawRound.draw()
    }
}

fun main() {
    DrawManager().draw1()
}