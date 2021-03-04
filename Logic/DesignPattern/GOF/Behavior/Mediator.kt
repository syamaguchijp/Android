// 複数のオブジェクト(Peer)の間でMediator（仲介者）を通してやりとりするため、クラス間が疎結合となる

class Mediator {
    private var peers =  arrayOf<Peer>()
    fun addPeer(peer: Peer) {
        peers += peer
    }
    fun send(message: String) {
        for (peer in peers) {
            peer.receive(message)
        }
    }
}

interface Peer {
    fun receive(message: String)
}

class Dog: Peer {
    override fun receive(message: String) {
        println("受け取ったメッセージは、${message}だワン")
    }
}

class Cat: Peer {
    override fun receive(message: String) {
        println("受け取ったメッセージは、${message}だニャン")
    }
}

fun main() {
    val mediator = Mediator()
    val dog = Dog()
    val cat = Cat()
    val dog2 = Dog()
    val cat2 = Cat()
    mediator.addPeer(dog)
    mediator.addPeer(cat)
    mediator.addPeer(dog2)
    mediator.addPeer(cat2)
    mediator.send("hogehoge")
}
