package me.pybsh.limbusbattle.plugin

import cloud.commandframework.execution.CommandExecutionCoordinator
import cloud.commandframework.paper.PaperCommandManager
import me.pybsh.limbusbattle.plugin.commands.LimbusBattleCommand
import me.pybsh.limbusbattle.plugin.events.LimbusBattleEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.function.Function

class LimbusBattlePlugin : JavaPlugin() {
    companion object {
        lateinit var instance: LimbusBattlePlugin
            private set
    }

    override fun onEnable() {
        instance = this

        server.pluginManager.registerEvents(LimbusBattleEvent, this)

        val commandManager = PaperCommandManager(
            this,
            CommandExecutionCoordinator.simpleCoordinator(),
            Function.identity(),
            Function.identity()
        )
//        Disabled due to cloud command framework not supporting brigadier at this moment with 1.21
//        if (commandManager.hasCapability(CloudBukkitCapabilities.NATIVE_BRIGADIER)) {
//            commandManager.registerBrigadier()
//        } else if (commandManager.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
//            commandManager.registerAsynchronousCompletions()
//        }

        commandManager.registerAsynchronousCompletions()
        commandManager.command(LimbusBattleCommand.registerCommand(commandManager))
    }
}
