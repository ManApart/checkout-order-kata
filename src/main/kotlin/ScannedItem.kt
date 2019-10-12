class ScannedItem(val name: String, val count: Float, var adjustedCost: Int) {
    fun adjustedPerUnitPrice(): Int {
        return (adjustedCost / count).toInt()
    }
}