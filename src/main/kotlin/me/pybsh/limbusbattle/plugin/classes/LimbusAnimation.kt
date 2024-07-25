package me.pybsh.limbusbattle.plugin.classes

import net.kyori.adventure.text.TextComponent

sealed interface LimbusAnimation<T> {
    data class Text(val textComponent: TextComponent) : LimbusAnimation<TextComponent>
    data class Sound(val sound: net.kyori.adventure.sound.Sound) : LimbusAnimation<Sound>
    data class Delay(val delay: Int) : LimbusAnimation<Int>
}
