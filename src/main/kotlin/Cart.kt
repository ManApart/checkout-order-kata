class Cart {
    private val scannableItems = mutableMapOf<String, Item>()
    private var scannedItemCost = 0

    fun register(item: Item) {
        scannableItems[item.name] = item
    }

    fun getBasePrice(itemName: String): Int {
        return scannableItems[itemName]?.unitCost
            ?: throw IllegalArgumentException("$itemName is not a scannable item.")
    }

    fun scan(itemName: String, units: Int) {
        val item = scannableItems[itemName]!!
        scannedItemCost = item.unitCost
    }

    fun getTotal(): Int {
        return scannedItemCost
    }


}