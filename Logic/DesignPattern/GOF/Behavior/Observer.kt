
class Observer {
    fun didChangeState() {
        println("didChangeState!")
    }
}

interface ViewModel {
    fun addObserver(observer: Observer)
    fun notifyObservers()
    fun changeState()
}

class MyViewModel(): ViewModel {
    var observers = arrayOf<Observer>()
    override fun addObserver(observer: Observer) {
        observers += observer
    }
    override fun notifyObservers() {
        for (i in observers) {
            i.didChangeState()
        }
    }
    override fun changeState() {
        notifyObservers()
    }
}

fun main() {
    var viewModel = MyViewModel()
    var observer = Observer()
    viewModel.addObserver(observer)

    viewModel.changeState()
}