package love.chihuyu.lifefunniments.fishingbattle

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent

object FishingBattleListener: Listener {

    @EventHandler
    fun onFish(e: PlayerFishEvent) {
        FishingStorage.datas.forEach {
            it.fished[e.player] = it.fished[e.player]?.inc() ?: 1
        }
    }
}