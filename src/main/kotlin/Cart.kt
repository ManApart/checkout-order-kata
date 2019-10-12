class Cart {
    private val scannableItems = mutableMapOf<String, Item>()
    private val scannedItems = mutableMapOf<Item, Float>()

    fun register(vararg items: Item) {
        items.forEach { item ->
            scannableItems[item.name] = item
        }
    }

    fun getBasePrice(itemName: String): Int {
        return scannableItems[itemName]?.unitCost
            ?: throw IllegalArgumentException("$itemName is not a scannable item.")
    }

    fun scan(itemName: String, units: Int = 1) {
        scan(itemName, units.toFloat())
    }

    fun scan(itemName: String, units: Float) {
        val item = scannableItems[itemName]!!
        scannedItems[item] = units
    }

    fun getTotal(): Int {
        return scannedItems.entries.sumBy { (it.key.unitCost * it.value).toInt()}
    }

}