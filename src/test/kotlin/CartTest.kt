import org.junit.Test
import kotlin.test.assertEquals

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
}