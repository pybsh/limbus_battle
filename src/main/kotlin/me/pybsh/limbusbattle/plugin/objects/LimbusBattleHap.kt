package me.pybsh.limbusbattle.plugin.objects

import com.github.shynixn.mccoroutine.bukkit.launch
import me.pybsh.limbusbattle.plugin.classes.LimbusAnimation
import me.pybsh.limbusbattle.plugin.classes.LimbusPlate
import me.pybsh.limbusbattle.plugin.objects.LimbusBattleObject.isPlayingAnimation
import me.pybsh.limbusbattle.plugin.objects.LimbusBattleObject.plugin
import net.kyori.adventure.text.Component.text
import kotlin.random.Random

object LimbusBattleHap {
    fun battle(plate1: LimbusPlate, plate2: LimbusPlate) = plugin.launch {
        while (plate1.coinCount > 0 && plate2.coinCount > 0) {
            hap(plate1, plate2)
        }

        val (winner, loser) = if (plate1.coins.isEmpty()) plate2 to plate1 else plate1 to plate2

        tossAllCoin(winner)

        isPlayingAnimation.remove(winner.entity)
        isPlayingAnimation.remove(loser.entity)
        loser.entity.damage(getPower(winner).toDouble(),winner.entity)
    }

    private suspend fun hap(plate1: LimbusPlate, plate2: LimbusPlate) {
        tossAllCoin(plate1)
        tossAllCoin(plate2)

        val power1 = getPower(plate1)
        val power2 = getPower(plate2)

        val ani1 = getCoinAnimation(plate1, plate2)
        val ani2 = getCoinAnimation(plate2, plate1)

        if (power1 > power2) {
            plate2.removeCoin()
        } else if (power2 > power1) {
            plate1.removeCoin()
        }

        LimbusBattleHapAnimation.animate(ani1, plate1.entity)
        val job = LimbusBattleHapAnimation.animate(ani2, plate2.entity)
        job.join()

    }

    private fun getPower(plate: LimbusPlate): Int {
        var power = plate.basePower

        plate.coins.forEach { c ->
            if ((plate.isMinusCoin && !c) || !plate.isMinusCoin && c) power += plate.weightPower
        }

        return power.coerceAtLeast(0)
    }

    private fun getCoinAnimation(plate1: LimbusPlate, plate2: LimbusPlate): ArrayList<LimbusAnimation<*>> {
        val animations: ArrayList<LimbusAnimation<*>> = arrayListOf()
        val str1 = StringBuilder("◇".repeat(plate1.coinCount))
        val str2 = StringBuilder("◇".repeat(plate2.coinCount))

        var power1 = plate1.basePower
        var power2 = plate2.basePower


        animations.add(LimbusAnimation.Text(text("$str1 ($power1) : $str2 ($power2)")))

        val maxCoins = maxOf(plate1.coins.size, plate2.coins.size)

        for (i in 0..<maxCoins) {
            power1 = (power1 + plate1.handleCoin(i, str1)).coerceAtLeast(0)
            power2 = (power2 + plate2.handleCoin(i, str2)).coerceAtLeast(0)

            animations.add(LimbusAnimation.Text(text("$str1 ($power1) : $str2 ($power2)")))
            animations.add(LimbusAnimation.Delay(1))
        }

        animations.add(LimbusAnimation.Delay(1000))

        return animations
    }

    private fun LimbusPlate.handleCoin(index: Int, builder: StringBuilder): Int {
        if (index >= coinCount) return 0

        val coin = coins[index]

        if ((isMinusCoin && !coin) || (!isMinusCoin && coin)) {
            builder.setCharAt(index, '◆')
            // todo: Put sound
        } else {
            builder.setCharAt(index, '◇')
            // todo: Put sound
        }

        if ((isMinusCoin && !coin) || (!isMinusCoin && coin)) {
            return weightPower
        }

        return 0
    }

    private fun tossAllCoin(plate: LimbusPlate) {
        plate.coins.replaceAll {
            tossCoin(plate.mentality / 100.0)
        }
    }

    private fun tossCoin(weight: Double): Boolean {
        val finalProbability = 0.5 + weight
        val randomValue = Random.nextDouble(0.0, 1.0)
        return randomValue <= finalProbability
    }
}