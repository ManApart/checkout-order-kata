import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CartTest {
    private val item = Item("test", 100)
    private val item2 = Item("test2", 150)

    @Test
    fun registerAnItem() {
        val cart = Cart()
        cart.register(item)
        assertEquals(item.unitCost, cart.getBasePrice(item.name))
    }

    @Test
    fun registerASecondItem() {
        val cart = Cart()
        cart.register(item2)
        assertEquals(item2.unitCost, cart.getBasePrice(item2.name))
    }

    @Test
    fun throwErrorWhenItemDoesNotExist() {
        assertFailsWith(IllegalArgumentException::class){Cart().getBasePrice("I do not exist")}
    }

    @Test
    fun scanAnItem() {
        val cart = Cart()
        cart.register(item)
        cart.scan(item.name, 1)
        assertEquals(item.unitCost, cart.getTotal())
    }

    @Test
    fun scanAnotherSingleItem() {
        val cart = Cart()
        cart.register(item2)
        cart.scan(item2.name, 1)
        assertEquals(item2.unitCost, cart.getTotal())
    }

    @Test
    fun scanTwoItems() {
        val cart = Cart()
        cart.register(item, item2)
        cart.scan(item.name, 1)
        cart.scan(item2.name, 1)
        assertEquals(item.unitCost + item2.unitCost, cart.getTotal())
    }

}