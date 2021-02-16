import java.util.Comparator

// 二分探索木
class BinaryTree<K, V>(var root: Node<K, V>?, val comparator: Comparator<in K>?) {

    // ノードを挿入する
    fun add(key: K, value: V) {

        if (root == null) {
            root = Node(key, value, null, null)
        } else {
            addNode(root!!, key, value)
        }
    }

    // nodeを根とする部分木に、keyとvalueで構成されるノードを挿入する
    private fun addNode(node: Node<K, V>, key: K, value: V) {

        val compResult = compare(key, node.key)
        if (compResult == 0) {
            // keyは既に存在するためためreturn
            return

        } else if (compResult < 0) {
            // 挿入するkeyの方がnode.keyより小さい場合、左に進む
            if (node.left == null) {
                node.left = Node(key, value, null, null)
            } else {
                // 既に存在する場合は、左下に進める
                addNode(node.left!!, key, value)
            }
            
        } else {
            // 挿入keyの方がnode.keyより大きい場合、右に進める
            if (node.right == null) {
                node.right = Node(key, value, null, null)
            } else {
                // 既に存在する場合は、右下に進める
                addNode(node.right!!, key, value)
            }
        }
    }

    // ノードを削除する
    fun remove(key: K): Boolean {

        var pointerNode = root
        var parentNode: Node<K, V>? = null
        var isLeftChild = true // pointerNodeはparentNodeの左子ノードかどうか

        // まず削除するキーを探索する
        while (true) {
            pointerNode = pointerNode ?: return false // これ以上進めない時はキーは存在しない

            val result = compare(key, pointerNode.key)
            if (result == 0) {
                break // 探索に成功したためbreakする
            } else {
                parentNode = pointerNode
                if (result < 0) {
                    isLeftChild = true
                    pointerNode = pointerNode.left
                } else {
                    isLeftChild = false
                    pointerNode = pointerNode.right
                }
            }
        }

        pointerNode = pointerNode ?: return false

        // 左の子ノードをもたないノードの削除
        if (pointerNode.left == null) {  
            if (pointerNode == root) {
                root = pointerNode.right
            } else if (isLeftChild) {
                parentNode?.left = pointerNode.right
            } else {
                parentNode?.right = pointerNode.right
            }

        // 左に子ノードがあるが右に子ノードがないノードの削除
        } else if (pointerNode.right == null) {
            if (pointerNode == root) {
                root = pointerNode.left
            } else if (isLeftChild) {
                parentNode?.left = pointerNode.left
            } else {
                parentNode?.right = pointerNode.left
            }

        // 左右の子ノードを持つノードの削除
        } else {
            // 左部分木の最大値を持ってきて据え置く
            parentNode = pointerNode
            var leftNode = pointerNode.left!!
            isLeftChild = true
            while (leftNode.right != null) {
                parentNode = leftNode
                leftNode = leftNode.right!!
                isLeftChild = false
            }
            pointerNode.key = leftNode.key
            pointerNode.value = leftNode.value
            if (isLeftChild) {
                parentNode?.left = leftNode.left
            } else {
                parentNode?.right = leftNode.right
            }
        }
        return true
    }

    fun search(key: K): V? {

        var rootNode = root
        while(true) {
            if (rootNode == null) {
                return null
            }
            val compResult = compare(key, rootNode.key)
            if (compResult == 0) {
                // 探索に成功したためreturn
                return rootNode.value
            } else if (compResult < 0) {
                // keyの方がrootNode.keyより小さい場合、左部分木へと探索を進める
                rootNode = rootNode.left
            } else {
                // keyの方がrootNode.keyより大きい場合、右部分木へと探索を進める
                rootNode = rootNode.right
            }
        }
    }

    // 第1引数が第2引数より小さい場合は負の整数、両方が等しい場合は0、第1引数が第2引数より大きい場合は正の整数を返す
    private fun compare(key1: K, key2: K): Int {
        
        if (comparator == null) {
            // Int型はComparableのため、こちらの分岐で。
            return (key1 as Comparable<K>).compareTo(key2)
        }
        return comparator.compare(key1, key2)
    }

    // すべてノードを昇順にダンプしていく
    fun dumpAll() {

       dumpNode(root)
    }

    private fun dumpNode(node: Node<K, V>?) {

        node ?: return
        dumpNode(node.left)
        println("node key=${node.key} value=${node.value}")
        dumpNode(node.right)
    }
}

data class Node<K, V>(var key: K, var value: V, var left: Node<K, V>?, var right: Node<K, V>?) {
}

fun main() {
    val binaryTree = BinaryTree<Int, String>(Node(10, "AAA", null, null), null) // Int型はComparableのためComparatorは不要
    binaryTree.add(11, "BBB")
    binaryTree.add(9, "CCC")
    binaryTree.add(13, "DDD")
    binaryTree.add(7, "EEE")
    binaryTree.add(8, "FFF")
    binaryTree.add(12, "GGG")
    binaryTree.remove(7)
    binaryTree.dumpAll()
    println("${binaryTree.search(8)}")

    val binaryTree2 = BinaryTree<String, String>(Node("H", "hhh", null, null), null)
    binaryTree2.add("I", "iii")
    binaryTree2.add("G", "ggg")
    binaryTree2.add("K", "kkk")
    binaryTree2.add("E", "eee")
    binaryTree2.add("F", "fff")
    binaryTree2.add("J", "jjj")
    binaryTree2.dumpAll()
}