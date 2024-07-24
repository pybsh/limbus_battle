package me.pybsh.limbusbattle.plugin.events

import me.pybsh.limbusbattle.plugin.classes.Plate
import me.pybsh.limbusbattle.plugin.objects.LimbusbattleHap
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

object LimbusbattleEvent : Listener {

    @EventHandler
    fun onEntityDamagedByEntity(event: EntityDamageByEntityEvent) {
        var damager = event.damager
        val target = event.entity

        if (damager is Arrow) {
            if ((event.damager as Arrow).shooter is Player) {
                damager = (event.damager as Arrow).shooter as Player
            }
        } else if (damager is Trident) {
            if ((event.damager as Trident).shooter is Player) {
                damager = (event.damager as Trident).shooter as Player
            }
        } else if (damager is Snowball) {
            if ((event.damager as Snowball).shooter is Player) {
                damager = (event.damager as Snowball).shooter as Player
            }
        } else if (damager is Egg) {
            if ((event.damager as Egg).shooter is Player) {
                damager = (event.damager as Egg).shooter as Player
            }
        } else if (damager is EnderPearl) {
            if ((event.damager as EnderPearl).shooter is Player) {
                damager = (event.damager as EnderPearl).shooter as Player
            }
        } else if (damager is Projectile) {
            if ((event.damager as Projectile).shooter is Player) {
                damager = (event.damager as Projectile).shooter as Player
            }
        }

        // todo : 임의의 값, 정신력 반영 안함.
        val p1 = Plate(3, 30, -12, -45, damager as Damageable, isMinusCoin = true)
        val p2 = Plate(4, 3, 2, 0, target as Damageable)

        LimbusbattleHap.battle(p1, p2)
        event.isCancelled = true
    }
}