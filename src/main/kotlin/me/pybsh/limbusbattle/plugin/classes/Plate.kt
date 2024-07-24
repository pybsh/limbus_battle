package me.pybsh.limbusbattle.plugin.classes

import org.bukkit.entity.Damageable

class Plate(
    var coinCount: Int,
    val basePower: Int,
    val weightPower: Int,
    val mentality: Int,
    val entity: Damageable,
    val isMinusCoin: Boolean = false
) {
    var coins: ArrayList<Boolean> = arrayListOf(false)
}