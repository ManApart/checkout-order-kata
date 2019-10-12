class Cart {
    private val scannableItems = mutableMapOf<String, Item>()

    fun register(item: Item) {
        scannableItems[item.name] = item
    }

    fun getBasePrice(itemName: String): Int {
        return scannableItems[itemName]!!.unitCost
    }
}