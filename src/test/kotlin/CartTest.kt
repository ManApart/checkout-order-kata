import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CartTest {
    private val item = Item("test", 100)
    private val item2 = Item("test2", 150)
    private val markdownDeal = MarkDownDeal("test", 20)
    private val markdownDeal2 = MarkDownDeal("test2", 50)

    @Test
    fun registerAnItem() {
        val cart = Cart()
        cart.register(item)
        assertEquals(item.unitCost, cart.getBasePrice(item.name))
    }

    @Test
    fun registerASecondItem() {
        val cart = Cart()
        cart.register(item2)
        assertEquals(item2.unitCost, cart.getBasePrice(item2.name))
    }

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

    @Test
    fun throwErrorWhenItemDoesNotExist() {
        assertFailsWith(IllegalArgumentException::class) { Cart().getBasePrice("I do not exist") }
        assertFailsWith(IllegalArgumentException::class) { Cart().scan("I do not exist") }
        assertFailsWith(IllegalArgumentException::class) { Cart().remove("I do not exist", 1f) }
    }

    @Test
    fun scanAnItem() {
        val cart = Cart()
        cart.register(item)
        cart.scan(item.name, 1)
        assertEquals(item.unitCost, cart.getTotal())
    }

    @Test
    fun scanAnotherSingleItem() {
        val cart = Cart()
        cart.register(item2)
        cart.scan(item2.name, 1)
        assertEquals(item2.unitCost, cart.getTotal())
    }

    @Test
    fun scanTwoUnitsOfOneItem() {
        val cart = Cart()
        cart.register(item)
        cart.scan(item.name, 2)
        assertEquals(item.unitCost * 2, cart.getTotal())
    }

    @Test
    fun scanningIncrementsItemCount() {
        val cart = Cart()
        cart.register(item)
        cart.scan(item.name, 1)
        cart.scan(item.name, 1)
        assertEquals(item.unitCost * 2, cart.getTotal())
    }

    @Test
    fun scanTwoItems() {
        val cart = Cart()
        cart.register(item, item2)
        cart.scan(item.name, 1)
        cart.scan(item2.name, 1)
        assertEquals(item.unitCost + item2.unitCost, cart.getTotal())
    }

    @Test
    fun partialUnits() {
        val cart = Cart()
        cart.register(item)
        cart.scan(item.name, 1.5f)
        assertEquals((item.unitCost * 1.5f).toInt(), cart.getTotal())
    }

    @Test
    fun defaultToSingleUnit() {
        val cart = Cart()
        cart.register(item)
        cart.scan(item.name)
        assertEquals(item.unitCost, cart.getTotal())
    }

    @Test
    fun canRemoveAnItem() {
        val cart = Cart()
        cart.register(item)

        assertEquals(0, cart.getTotal())

        cart.scan(item.name)
        assertEquals(item.unitCost, cart.getTotal())

        cart.remove(item.name, 1f)

        assertEquals(0, cart.getTotal())
    }

    @Test
    fun cannotHaveNegativeItemCounts() {
        val cart = Cart()
        cart.register(item)

        assertEquals(0, cart.getTotal())

        cart.remove(item.name, 1f)

        assertEquals(0, cart.getTotal())
    }

}