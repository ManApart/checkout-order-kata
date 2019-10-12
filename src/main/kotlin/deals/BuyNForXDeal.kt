package deals

import ScannedItem
import kotlin.math.min

class BuyNForXDeal(private val itemName: String, val count: Float, val totalCost: Int, private val limit: Float? = null) : Deal {

    override fun apply(item: ScannedItem) {
        if (itemName == item.name && item.count >= count) {
            val maxItems = min(item.count, limit ?: item.count)
            val multiplier = (maxItems / count).toInt()
            val remainder = (item.count - (multiplier * count)).toInt()
            item.adjustedCost = (totalCost * multiplier) + (remainder * item.adjustedPerUnitPrice())
        }
    }
}