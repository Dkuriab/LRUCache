import java.security.Key

class LRUCacheImpl<KeyType, ValueType>(private val capacity: Int): LRUCache<KeyType, ValueType> {
    private val values: HashMap<KeyType, Node<KeyType, ValueType>> = hashMapOf()
    private var head: Node<KeyType, ValueType>? = null
    private var tail: Node<KeyType, ValueType>? = null

    init {
        assert(capacity > 0) { "Capacity must be positive" }
    }

    override fun cache(key: KeyType, value: ValueType) {
        removeNode(key)
        addNewNode(Node(key, value))
        assert(head?.value == value) { "Wrong value priority" }
        assert(values.size <= capacity) { "Cache overflow" }
    }

    override fun get(key: KeyType): ValueType? {
        val size = values.size

        val value = values[key]

        return if (value != null) {
            cache(key, value.value)
            assert(size == values.size) { "Values count should not change" }
            value.value
        } else {
            null
        }

    }

    private fun addNewNode(node: Node<KeyType, ValueType>) {
        assert(!values.containsKey(node.key)) { " Can't add new node if it exists " }

        if (values.size == capacity) {
            values.remove(tail?.key)
            tail = tail?.previous
        }

        node.next = head
        node.previous = null
        head?.previous = node
        head = node

        if (values.size == 0) {
            tail = node
        }

        values[node.key] = node
    }

    private fun removeNode(key: KeyType) {
        val removedNode = values.remove(key)

//      previous  <->  node  <->  next
//                <------------>

        if (removedNode == tail) {
            tail = removedNode?.previous
        }

        val previous = removedNode?.previous
        val next = removedNode?.next

        previous?.next = next
        next?.previous = previous

    }

    data class Node<KeyType, ValueType>(
        val key: KeyType,
        var value: ValueType,
        var previous: Node<KeyType, ValueType>? = null,
        var next: Node<KeyType, ValueType>? = null,
    )
}