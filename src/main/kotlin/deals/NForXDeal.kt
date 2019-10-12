package deals

import Item

class NForXDeal(private val itemName: String, private val count: Int, val totalCost: Int) : Deal {

    override fun apply(adjustedCosts: MutableMap<Item, Int>) {
        adjustedCosts.keys.filter { itemName == it.name }.forEach { item ->
            adjustedCosts[item] = totalCost
        }
    }
}