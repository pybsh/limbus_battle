package me.pybsh.limbusbattle.plugin

import cloud.commandframework.execution.CommandExecutionCoordinator
import cloud.commandframework.paper.PaperCommandManager
import me.pybsh.limbusbattle.plugin.commands.LimbusbattleCommand
import me.pybsh.limbusbattle.plugin.events.LimbusbattleEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.function.Function

class LimbusbattlePlugin : JavaPlugin() {
    companion object {
        lateinit var instance: LimbusbattlePlugin
            private set
    }

    override fun onEnable() {
        instance = this

        server.pluginManager.registerEvents(LimbusbattleEvent, this)

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
        commandManager.command(LimbusbattleCommand.registerCommand(commandManager))
    }
}
