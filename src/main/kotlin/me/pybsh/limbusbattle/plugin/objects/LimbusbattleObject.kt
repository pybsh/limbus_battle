package me.pybsh.limbusbattle.plugin.objects

import me.pybsh.limbusbattle.plugin.LimbusbattlePlugin
import org.bukkit.entity.Damageable

object LimbusbattleObject {
    val plugin = LimbusbattlePlugin.instance
    val server = plugin.server
    val isPlayingAnimation: ArrayList<Damageable> = arrayListOf()
}
