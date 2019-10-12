package deals

import ScannedItem

class NForXDeal(private val itemName: String, private val count: Float, val totalCost: Int) : Deal {

    override fun apply(item: ScannedItem) {
        if (itemName == item.name && item.count == count) {
            item.adjustedCost = totalCost
        }
    }
}