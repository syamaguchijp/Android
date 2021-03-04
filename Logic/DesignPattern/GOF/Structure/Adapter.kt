open class Adaptee() {
    fun doAdaptee() {
        println("Adaptee > func methodA")
    }
}

interface Target {
    fun doTarget()
}

// 既存のAdapteeクラスに手を加えずに、Targetプロトコルを適合させる。
// 新しい版（Adaptee）に古い版（Target）のメソッドを実装するような場合。
class Adapter(): Adaptee(), Target {
    
    override fun doTarget() {
        super.doAdaptee()
    }
}

fun main() {
    Adapter().doTarget()
}
