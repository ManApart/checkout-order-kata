package deals

import ScannedItem

class MarkDownDeal(private val itemName: String, val unitCostOff: Int) : Deal {
    override fun apply(item: ScannedItem) {
        if (itemName == item.name){
            item.adjustedCost = item.adjustedCost - unitCostOff
        }
    }
}