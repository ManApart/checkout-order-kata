package deals

import ScannedItem
import kotlin.math.max

class MarkDownDeal(private val itemName: String, val unitCostOff: Int) : Deal {
    override fun apply(item: ScannedItem) {
        if (itemName == item.name) {
            item.adjustedCost = max(0, item.adjustedCost - unitCostOff)
        }
    }
}