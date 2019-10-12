package deals

import Cart
import item
import item2
import org.junit.Test
import kotlin.test.assertEquals

val nGetMForXDeal = BuyNGetMForXOff(item.name, 1f, 1f, item.unitCost)

class BuyNGetMForXOffTest {

    @Test
    fun buyOneGetOneFree() {
        val cart = Cart()
        cart.register(item)
        cart.register(nGetMForXDeal)
        cart.scan(item.name, 2f)
        assertEquals(item.unitCost, cart.getTotal())
    }

    @Test
    fun onlyEffectiveForSelectItems() {
        val cart = Cart()
        cart.register(item2)
        cart.register(nGetMForXDeal)
        cart.scan(item2.name, 2f)
        assertEquals(item2.unitCost * 2, cart.getTotal())
    }

    @Test
    fun buyOneGetOneHalfOff() {
        val cart = Cart()
        val deal = BuyNGetMForXOff(item.name, 1f, 1f, item.unitCost / 2)
        cart.register(item)
        cart.register(deal)
        cart.scan(item.name, 2f)
        assertEquals((item.unitCost * 1.5f).toInt(), cart.getTotal())
    }


    @Test
    fun doesNothingIfBelowUnitCount() {
        val cart = Cart()
        cart.register(item)
        val deal = BuyNGetMForXOff(item.name, 2f, 1f, item.unitCost)
        cart.register(deal)
        cart.scan(item.name, 1f)
        assertEquals(item.unitCost, cart.getTotal())
    }

    @Test
    fun buyTwoGetOneFreeX2() {
        val cart = Cart()
        cart.register(item)
        cart.register(BuyNGetMForXOff(item.name, 2f, 1f, item.unitCost))
        cart.scan(item.name, 6)
        assertEquals(item.unitCost * 4, cart.getTotal())
    }

    @Test
    fun remaindersAreNormalPrice() {
        val cart = Cart()
        cart.register(item)
        cart.register(BuyNGetMForXOff(item.name, 2f, 1f, item.unitCost))
        cart.scan(item.name, 7)
        assertEquals(item.unitCost * 5, cart.getTotal())
    }


}