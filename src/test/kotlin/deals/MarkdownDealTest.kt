package deals

import org.junit.Test
import kotlin.test.assertEquals

import Cart
import item
import item2

val markdownDeal = MarkDownDeal("test", 20)
val markdownDeal2 = MarkDownDeal("test2", 50)

class MarkdownDealTest {
    @Test
    fun simpleAMarkdownDeal() {
        val cart = Cart()
        cart.register(item)
        cart.register(markdownDeal)
        cart.scan(item.name)
        assertEquals(item.unitCost - markdownDeal.unitCostOff, cart.getTotal())
    }

    @Test
    fun twoMarkdownDeals() {
        val cart = Cart()
        cart.register(item, item2)
        cart.register(markdownDeal, markdownDeal2)
        cart.scan(item.name)
        cart.scan(item2.name)
        val expectedCost = item.unitCost - markdownDeal.unitCostOff + item2.unitCost - markdownDeal2.unitCostOff
        assertEquals(expectedCost, cart.getTotal())
    }

}