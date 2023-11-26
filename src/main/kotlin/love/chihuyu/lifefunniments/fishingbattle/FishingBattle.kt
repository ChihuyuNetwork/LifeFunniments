package love.chihuyu.lifefunniments.fishingbattle

import org.bukkit.OfflinePlayer

class FishingBattle(val owner: OfflinePlayer) {
    val fished = mutableMapOf<OfflinePlayer, Int>()
}