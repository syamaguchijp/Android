// 機能のクラス階層と実装のクラス階層を分離し、依存性を注入して結びつける
// クラスの爆発を防ぐ

// 機能
open class Drawer constructor(val impl: DrawImpl) {
    fun draw() {
        impl.print()
    }
}
class SubDrawer(impl: DrawImpl): Drawer(impl) {
    fun multiDraw() {
        for (i in 1..5) {
            super.draw()
        }
    }
}

// 実装
interface DrawImpl {
    fun print()
}
class CircleDrawImpl(): DrawImpl {
    override fun print() {
        println("print Circle")
    }
}
class RectangleDrawImpl(): DrawImpl {
    override fun print() {
        println("print Rectangle")
    }
}


fun main() {
    val drawer = Drawer(CircleDrawImpl())
    drawer.draw()

    val subDrawer = SubDrawer(RectangleDrawImpl())
    subDrawer.multiDraw()
}