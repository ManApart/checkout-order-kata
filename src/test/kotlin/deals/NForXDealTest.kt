package deals

import item2
import nForXDeal
import org.junit.Test
import kotlin.test.assertEquals

import Cart

class NForXDealTest {

    @Test
    fun registerANForXDeal() {
        val cart = Cart()
        cart.register(item2)
        cart.register(nForXDeal)
        cart.scan(item2.name, 3)
        assertEquals(nForXDeal.totalCost, cart.getTotal())
    }

}