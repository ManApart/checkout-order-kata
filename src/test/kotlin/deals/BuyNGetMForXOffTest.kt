package deals

import Cart
import item
import org.junit.Test
import kotlin.test.assertEquals

class BuyNGetMForXOffTest {

    @Test
    fun buyOneGetOneFree() {
        val cart = Cart()
        val deal = BuyNGetMForXOff(item.name, 1f, 1f, item.unitCost)
        cart.register(item)
        cart.register(deal)
        cart.scan(item.name, 2f)
        assertEquals(item.unitCost, cart.getTotal())
    }



}