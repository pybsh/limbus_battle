package me.pybsh.limbusbattle.plugin.objects

import me.pybsh.limbusbattle.plugin.classes.Plate
import net.kyori.adventure.text.Component.text
import kotlin.random.Random

object LimbusbattleHap {
    fun battle(plate1: Plate, plate2: Plate) {
        if(plate1.coins.size <= 0) {
            tossAllCoin(plate2)
            plate1.entity.damage(getPower(plate2).toDouble())

        }
        else if(plate2.coins.size <= 0) {
            tossAllCoin(plate1)
            plate2.entity.damage(getPower(plate1).toDouble())
        }
        else {
            hap(plate1, plate2)
        }
    }

    private fun hap(plate1: Plate, plate2: Plate) {
        tossAllCoin(plate1)
        tossAllCoin(plate2)

        val power1 = getPower(plate1)
        val power2 = getPower(plate2)

        val coinStr1 = getCoinString(plate1)
        val coinStr2 = getCoinString(plate2)

        plate1.entity.sendMessage(text("my power: $coinStr1 = $power1 (${plate1.coinCount})"))
        plate1.entity.sendMessage(text("enemy power: $coinStr2 = $power2 (${plate2.coinCount})\n"))
        plate2.entity.sendMessage(text("my power: $coinStr2 = $power2 (${plate2.coinCount})"))
        plate2.entity.sendMessage(text("enemy power: $coinStr1 = $power1 (${plate1.coinCount})\n"))

        if(power1 > power2) {
            plate2.coinCount -= 1
            plate1.entity.sendMessage(text("hap win\n\n"))
        }
        else if(power2 > power1) {
            plate1.coinCount -= 1
            plate2.entity.sendMessage(text("hap win\n\n"))
        }

        battle(plate1, plate2)
    }

    private fun getPower(plate: Plate): Int {
        var power = plate.basePower
        plate.coins.forEach { c ->
            if((plate.isMinusCoin && !c) || !plate.isMinusCoin && c) power += plate.weightPower
        }
        return power
    }

    private fun getCoinString(plate: Plate): String {
        var str = ""
        plate.coins.forEach { c ->
            if((plate.isMinusCoin && !c) || !plate.isMinusCoin && c) str += "@"
            else str += "O"
        }
        return str
    }

    private fun tossAllCoin(plate: Plate) {
        plate.coins.clear()
        for (i in 1..plate.coinCount) {
            plate.coins.add(tossCoin(plate.mentality / 100.0))
        }
    }

    private fun tossCoin(weight: Double): Boolean {
        val finalProbability = 0.5 + weight
        val randomValue = Random.nextDouble(0.0, 1.0)
        return randomValue <= finalProbability
    }
}