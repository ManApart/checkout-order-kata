import kotlin.math.max

class Cart {
    private val scannableItems = mutableMapOf<String, Item>()
    private val scannedItems = mutableMapOf<Item, Float>()

    fun register(vararg items: Item) {
        items.forEach { item ->
            scannableItems[item.name] = item
        }
    }

    private fun getRegisteredItem(itemName: String): Item {
        return scannableItems[itemName] ?: throw IllegalArgumentException("$itemName is not a scannable item.")
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
        return scannedItems.entries.sumBy { (it.key.unitCost * it.value).toInt() }
    }


}