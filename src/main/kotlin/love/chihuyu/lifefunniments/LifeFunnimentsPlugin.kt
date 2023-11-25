package love.chihuyu.lifefunniments

import org.bukkit.plugin.java.JavaPlugin

class LifeFunnimentsPlugin: JavaPlugin() {
    companion object {
        lateinit var LifeFunnimentsPlugin: JavaPlugin
    }

    init {
        LifeFunnimentsPlugin = this
    }
}