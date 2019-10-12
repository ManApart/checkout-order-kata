package deals

import org.junit.Test
import kotlin.test.assertEquals

import Cart
import item
import item2
import markdownDeal
import markdownDeal2

class MarkdownDealTest {
    @Test
    fun registerAMarkdownDeal() {
        val cart = Cart()
        cart.register(item)
        cart.register(markdownDeal)
        cart.scan(item.name)
        assertEquals(item.unitCost - markdownDeal.unitCostOff, cart.getTotal())
    }

    @Test
    fun registerTwoMarkdownDeals() {
        val cart = Cart()
        cart.register(item, item2)
        cart.register(markdownDeal, markdownDeal2)
        cart.scan(item.name)
        cart.scan(item2.name)
        val expectedCost = item.unitCost - markdownDeal.unitCostOff + item2.unitCost - markdownDeal2.unitCostOff
        assertEquals(expectedCost, cart.getTotal())
    }

}