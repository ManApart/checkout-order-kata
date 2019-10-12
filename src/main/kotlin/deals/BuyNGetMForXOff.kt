package deals

import ScannedItem

class BuyNGetMForXOff(
    private val itemName: String,
    private val requiredCount: Float,
    discountCount: Float,
    private val discountCostOff: Int
) : Deal {
    private val requiredRatio = requiredCount / (requiredCount + discountCount)

    override fun apply(item: ScannedItem) {
        if (itemName == item.name && item.count > requiredCount) {
            val unitCost = item.adjustedPerUnitPrice()
            val discountCost = unitCost - discountCostOff
            val required = requiredRatio * item.count
            val discounted = item.count - required
            item.adjustedCost = (required * unitCost).toInt() + (discounted * discountCost).toInt()
        }
    }
}