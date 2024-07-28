package me.pybsh.limbusbattle.plugin.objects

import me.pybsh.limbusbattle.plugin.LimbusBattlePlugin
import org.bukkit.entity.Damageable

object LimbusBattleObject {
    val plugin = LimbusBattlePlugin.instance
    val server = plugin.server
    val isPlayingAnimation: ArrayList<Damageable> = arrayListOf()
}
