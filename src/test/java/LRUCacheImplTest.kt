import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LRUCacheImplTest {

    @Test
    fun `added value is saved`() {
        val cache: LRUCache<Int, String> = LRUCacheImpl(1)
        cache.cache(0, "0")
        assertEquals("0", cache.get(0))
    }

    @Test
    fun `create cache with negative capacity`() {
        assertThrows(AssertionError::class.java) { LRUCacheImpl<Int, String>(-1) }
    }

    @Test
    fun `get not existing value`() {
        val cache = LRUCacheImpl<Int, String>(4)
        assertEquals(null, cache.get(0))
    }

    @Test
    fun `clears overhead correctly`() {
        val cache = LRUCacheImpl<Int, String>(2)
        cache.cache(0, "0")
        cache.cache(1, "1")
        cache.cache(2, "2")
        assertEquals(null, cache.get(0))
        assertEquals("1", cache.get(1))
        assertEquals("2", cache.get(2))
    }


    @Test
    fun `update frequently used keys priority`() {
        val cache = LRUCacheImpl<Int, String>(3)
        cache.cache(0, "0")
        cache.cache(1, "1")
        cache.cache(0, "0-new")
        cache.cache(2, "2")
        cache.cache(3, "3")

        assertEquals("0-new", cache.get(0))
        assertEquals(null, cache.get(1))
        assertEquals("2", cache.get(2))
        assertEquals("3", cache.get(3))
    }

}