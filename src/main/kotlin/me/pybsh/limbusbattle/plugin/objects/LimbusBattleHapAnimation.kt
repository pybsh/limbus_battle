package me.pybsh.limbusbattle.plugin.objects

import com.github.shynixn.mccoroutine.bukkit.launch
import kotlinx.coroutines.*
import me.pybsh.limbusbattle.plugin.classes.LimbusAnimation
import me.pybsh.limbusbattle.plugin.objects.LimbusBattleObject.plugin
import org.bukkit.entity.Damageable

object LimbusBattleHapAnimation {
    fun animate(animation: ArrayList<LimbusAnimation<*>>, entity: Damageable) = plugin.launch {
        animation.forEach { info ->
            when (info) {
                is LimbusAnimation.Text -> {
                    entity.sendActionBar(info.textComponent)
//                    entity.sendMessage(info.textComponent)
                    delay(20L)
                }

                is LimbusAnimation.Sound -> {
//                        entity.playSound() // todo: playsound
                }

                is LimbusAnimation.Delay -> {
//                    entity.sendMessage("===")
                    delay(info.delay.toLong())
                }
            }
        }
    }

}