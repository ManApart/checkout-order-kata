package deals

import item2
import org.junit.Test
import kotlin.test.assertEquals

import Cart

val nForXDeal = NForXDeal("test2", 3f, 500)

class NForXDealTest {

    @Test
    fun simpleANForXDeal() {
        val cart = Cart()
        cart.register(item2)
        cart.register(nForXDeal)
        cart.scan(item2.name, nForXDeal.count)
        assertEquals(nForXDeal.totalCost, cart.getTotal())
    }

    @Test
    fun doesNothingIfBelowUnitCount() {
        val cart = Cart()
        cart.register(item2)
        cart.register(nForXDeal)
        cart.scan(item2.name, 2)
        assertEquals(item2.unitCost * 2, cart.getTotal())
    }

    @Test
    fun canDoMultiples() {
        val cart = Cart()
        cart.register(item2)
        cart.register(nForXDeal)
        cart.scan(item2.name, nForXDeal.count * 2)
        assertEquals(nForXDeal.totalCost * 2, cart.getTotal())
    }

    @Test
    fun remaindersAreNormalCost() {
        val cart = Cart()
        cart.register(item2)
        cart.register(nForXDeal)
        cart.scan(item2.name, nForXDeal.count * 2 + 1)
        assertEquals(nForXDeal.totalCost * 2 + item2.unitCost, cart.getTotal())
    }

}