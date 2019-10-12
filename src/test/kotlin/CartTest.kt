import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CartTest {

    @Test
    fun registerAnItem() {
        val cart = Cart()
        val item = Item("test", 100)
        cart.register(item)
        assertEquals(item.unitCost, cart.getBasePrice(item.name))
    }

    @Test
    fun registerASecondItem() {
        val cart = Cart()
        val item = Item("test2", 150)
        cart.register(item)
        assertEquals(item.unitCost, cart.getBasePrice(item.name))
    }

    @Test
    fun throwErrorWhenItemDoesNotExist() {
        assertFailsWith(IllegalArgumentException::class){Cart().getBasePrice("I do not exist")}
    }


    @Test
    fun scanAnItem() {
        val cart = Cart()
        val item = Item("test", 100)
        cart.register(item)
        cart.scan(item.name, 1)
        assertEquals(item.unitCost, cart.getTotal())
    }

}