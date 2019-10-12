package deals

import ScannedItem

class BuyNGetMForXOff(
    private val itemName: String,
    val requiredCount: Float,
    val discountCount: Float,
    val discountCostOff: Int
) : Deal {

    override fun apply(item: ScannedItem) {
        if (itemName == item.name) {
            val unitCost = item.adjustedPerUnitPrice()
            val discountCost = unitCost - discountCostOff
            item.adjustedCost = (requiredCount * unitCost).toInt() + (discountCount * discountCost).toInt()
        }
    }
}