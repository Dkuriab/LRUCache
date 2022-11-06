class LRUCacheImpl<KeyType, ValueType>(capacity: Int): LRUCache<KeyType, ValueType> {
    init {
        assert(capacity > 0) {
            println(" Capacity must be positive ")
        }
    }

    override fun cache(key: KeyType, value: ValueType) {
        TODO("Not yet implemented")
    }

    override fun get(key: KeyType): ValueType? {
        TODO("Not yet implemented")
    }

    private class Node()
}