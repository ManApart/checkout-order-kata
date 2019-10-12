package deals

import ScannedItem

class NForXDeal(private val itemName: String, val count: Float, val totalCost: Int) : Deal {

    override fun apply(item: ScannedItem) {
        if (itemName == item.name && item.count >= count) {
            val multiplier = (item.count / count).toInt()
            item.adjustedCost = totalCost * multiplier
        }
    }
}