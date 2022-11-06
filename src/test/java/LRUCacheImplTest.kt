import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LRUCacheImplTest {

    @Test
    fun `added value is saved`() {
        val cache = LRUCacheImpl<Int, String>(1)

    }
}