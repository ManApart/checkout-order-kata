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

    @Test
    fun buyOneGetOneHalfOff() {
        val cart = Cart()
        val deal = BuyNGetMForXOff(item.name, 1f, 1f, item.unitCost/2)
        cart.register(item)
        cart.register(deal)
        cart.scan(item.name, 2f)
        assertEquals((item.unitCost * 1.5f).toInt(), cart.getTotal())
    }



}