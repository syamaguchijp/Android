
// クロージャを利用したコールバックの例

typealias MyCompletionClosure = (isSuccess: Boolean, errorCode: String) -> Unit
 
class User() {
    fun executeClosure(completion: MyCompletionClosure) {
        println("executeClosure")
        completion(true, "NO_ERROR")
    }
}

fun main() { 
    val user = User()
    /*
    val completion = { isSuccess: Boolean, errorCode: String -> 
        print("complete!!! ${isSuccess}")
    }*/
    user.executeClosure({ isSuccess: Boolean, errorCode: String -> 
        print("complete!!! ${isSuccess}")
    }) 
}