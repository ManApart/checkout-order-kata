package deals

import Item

class MarkDownDeal(private val itemName: String, val unitCostOff: Int) : Deal {
    override fun apply(adjustedCosts: MutableMap<Item, Int>) {
        adjustedCosts.keys.filter { itemName == it.name }.forEach { item ->
            adjustedCosts[item] = adjustedCosts[item]!! - unitCostOff
        }
    }
}