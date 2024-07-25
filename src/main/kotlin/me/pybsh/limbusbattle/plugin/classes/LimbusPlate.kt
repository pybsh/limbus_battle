package me.pybsh.limbusbattle.plugin.classes

import org.bukkit.entity.Damageable

data class LimbusPlate(
    val initialCoinCount: Int,
    val basePower: Int,
    val weightPower: Int,
    val mentality: Int,
    val entity: Damageable,
    val isMinusCoin: Boolean = false
) {
    val coins: ArrayList<Boolean> = ArrayList(List(initialCoinCount) { false })

    val coinCount
        get() = coins.size

    fun removeCoin() {
        if (coins.isEmpty()) return
        coins.removeLast()
    }
}