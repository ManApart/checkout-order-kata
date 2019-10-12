package deals

import Item

interface Deal {
    fun apply(adjustedCosts: MutableMap<Item, Int>)
}