package deals

import ScannedItem

interface Deal {
    fun apply(item: ScannedItem)
}