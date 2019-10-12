class Cart {
    private val scannableItems = mutableMapOf<String, Item>()

    fun register(item: Item) {
        scannableItems[item.name] = item
    }

    fun getBasePrice(itemName: String): Int {
        return scannableItems[itemName]?.unitCost
            ?: throw IllegalArgumentException("$itemName is not a scannable item.")
    }

    fun scan(itemName: String, units: Int) {

    }

    fun getTotal(): Int {
        return 100
    }


}