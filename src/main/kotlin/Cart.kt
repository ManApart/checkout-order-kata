import deals.Deal
import kotlin.math.max

class Cart {
    private val scannableItems = mutableMapOf<String, Item>()
    private val scannedItems = mutableMapOf<Item, Float>()
    private var deals = mutableListOf<Deal>()

    fun register(vararg items: Item) {
        items.forEach { item ->
            scannableItems[item.name.toLowerCase()] = item
        }
    }

    fun register(vararg deals: Deal) {
        this.deals.addAll(deals)
    }

    private fun getRegisteredItem(itemName: String): Item {
        return scannableItems[itemName.toLowerCase()]
            ?: throw IllegalArgumentException("$itemName is not a scannable item.")
    }

    fun getBasePrice(itemName: String): Int {
        return getRegisteredItem(itemName).unitCost
    }

    fun scan(itemName: String, units: Int = 1) {
        scan(itemName, units.toFloat())
    }

    fun scan(itemName: String, units: Float) {
        val item = getRegisteredItem(itemName)
        scannedItems[item] = (scannedItems[item] ?: 0f) + units
    }

    fun remove(itemName: String, units: Float) {
        val item = getRegisteredItem(itemName)
        val newCount = (scannedItems[item] ?: 0f) - units
        scannedItems[item] = max(0f, newCount)
    }

    fun getTotal(): Int {
        val adjustedCosts =
            scannedItems.mapValues { (item, units) -> (getBasePrice(item.name) * units).toInt() }.toMutableMap()
        deals.forEach {
            it.apply(adjustedCosts)
        }
        return adjustedCosts.values.sum()
    }


}