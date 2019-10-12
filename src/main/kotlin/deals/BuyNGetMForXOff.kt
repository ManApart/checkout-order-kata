package deals

import ScannedItem
import kotlin.math.min
import kotlin.math.roundToInt

class BuyNGetMForXOff(
    private val itemName: String,
    private val requiredCount: Float,
    discountCount: Float,
    private val discountCostOff: Int,
    private val limit: Float? = null
) : Deal {
    private val requiredRatio = requiredCount / (requiredCount + discountCount)

    override fun apply(item: ScannedItem) {
        if (itemName == item.name && item.count > requiredCount) {
            val unitCost = item.adjustedPerUnitPrice()
            val discountCost = unitCost - discountCostOff

            val maxItems = min(limit ?: item.count, item.count)

            val required = (requiredRatio * maxItems).roundToInt()
            val discounted = ((1-requiredRatio) * maxItems).roundToInt()
            val leftOvers = item.count - required - discounted

            item.adjustedCost = ((required + leftOvers) * unitCost).toInt() + (discounted * discountCost)
        }
    }
}