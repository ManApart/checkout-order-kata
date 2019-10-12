class Cart {
    private val scannableItems = mutableMapOf<String, Item>()
    private val scannedItems = mutableMapOf<Item, Int>()

    fun register(vararg items: Item) {
        items.forEach { item ->
            scannableItems[item.name] = item
        }
    }

    fun getBasePrice(itemName: String): Int {
        return scannableItems[itemName]?.unitCost
            ?: throw IllegalArgumentException("$itemName is not a scannable item.")
    }

    fun scan(itemName: String, units: Int) {
        val item = scannableItems[itemName]!!
        scannedItems[item] = units
    }

    fun getTotal(): Int {
        return scannedItems.entries.sumBy { it.key.unitCost * it.value}
    }

}