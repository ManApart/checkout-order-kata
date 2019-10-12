import deals.Deal
import kotlin.math.max

class Cart {
    private val scannableItems = mutableMapOf<String, Item>()
    private val scannedItemCounts = mutableMapOf<Item, Float>()
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
        scannedItemCounts[item] = (scannedItemCounts[item] ?: 0f) + units
    }

    fun remove(itemName: String, units: Float) {
        val item = getRegisteredItem(itemName)
        val newCount = (scannedItemCounts[item] ?: 0f) - units
        scannedItemCounts[item] = max(0f, newCount)
    }

    fun getTotal(): Int {
        val adjustedItems = scannedItemCounts.map { (item, units) -> createScannedItem(item, units) }
        adjustedItems.forEach { item ->
            deals.forEach { deal ->
                deal.apply(item)
            }
        }
        return adjustedItems.sumBy { it.adjustedCost }
    }

    private fun createScannedItem(item: Item, units: Float): ScannedItem {
        return ScannedItem(item.name, units, (getBasePrice(item.name) * units).toInt())
    }


}