package love.chihuyu.lifefunniments

import love.chihuyu.lifefunniments.fishingbattle.FishingBattleCommand
import love.chihuyu.lifefunniments.fishingbattle.FishingBattleListener
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin

class LifeFunnimentsPlugin: JavaPlugin() {
    companion object {
        lateinit var LifeFunnimentsPlugin: JavaPlugin
        lateinit var mm: MiniMessage
    }

    init {
        LifeFunnimentsPlugin = this
        mm = MiniMessage.miniMessage()
    }

    override fun onEnable() {
        super.onEnable()

        FishingBattleCommand.register()

        server.pluginManager.registerEvents(FishingBattleListener, this)

        logger.info("LifeFunniments has loaded.")
    }

    override fun onDisable() {
        super.onDisable()
        logger.info("LifeFunniments has unloaded.")
    }
}