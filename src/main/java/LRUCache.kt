
interface LRUCache<KeyType, ValueType> {
    fun cache(key: KeyType, value: ValueType)
    fun get(key: KeyType): ValueType?
}