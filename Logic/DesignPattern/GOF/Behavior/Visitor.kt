
// 異種のオブジェクトからなるコレクションに適用される

interface Shape {
    fun accept(visitor: Visitor)
}

class Circle constructor(val radius: Float): Shape {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class Rectangle constructor(val length: Float): Shape {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class ShapeCollection {
    var shapes: Array<Shape>
    init {
        shapes = arrayOf(Circle(5F), Circle(10F), Rectangle(20F))
    }
    fun accept(visitor: Visitor) {
        for (shape in shapes) {
            shape.accept(visitor)
        }
    }
}

// 新しい処理を追加したいときは新しいVisitorを作ればよい

interface Visitor {
    fun visit(shape: Circle)
    fun visit(shape: Rectangle)
}

class AreaVisitor: Visitor {
    var total: Float = 0F
    override fun visit(shape: Circle) {
        total += (3.14F * shape.radius * shape.radius)
    }
    override fun visit(shape: Rectangle) {
        total += shape.length * shape.length
    }
}

class EdgeVisitor: Visitor {
    var total: Float = 0F
    override fun visit(shape: Circle) {
        total += 1
    }
    override fun visit(shape: Rectangle) {
        total += 4
    }
}

fun main() {
    val shapes = ShapeCollection()
    val areaVisitor = AreaVisitor()
    shapes.accept(areaVisitor)
    println("${areaVisitor.total}")
}
