package deals

import Cart
import item
import item2
import org.junit.Test
import kotlin.test.assertEquals

val nForXDeal = BuyNForXDeal("test2", 3f, 500)

class BuyNForXDealTest {

    @Test
    fun simpleANForXDeal() {
        val cart = Cart()
        cart.register(item2)
        cart.register(nForXDeal)
        cart.scan(item2.name, nForXDeal.count)
        assertEquals(nForXDeal.totalCost, cart.getTotal())
    }

    @Test
    fun doNothingForOtherItems() {
        val cart = Cart()
        cart.register(item)
        cart.register(nForXDeal)
        cart.scan(item.name, nForXDeal.count)
        assertEquals((item.unitCost * nForXDeal.count).toInt(), cart.getTotal())
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

    @Test
    fun dealCanBeLimited() {
        val cart = Cart()
        cart.register(item)
        cart.register(BuyNForXDeal("test", 3f, 50, 3f))
        cart.scan(item.name, 6)
        assertEquals(50 + item.unitCost * 3, cart.getTotal())
    }

}