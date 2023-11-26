package love.chihuyu.lifefunniments.fishingbattle

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import love.chihuyu.lifefunniments.Command
import love.chihuyu.lifefunniments.LifeFunnimentsPlugin.Companion.LifeFunnimentsPlugin
import love.chihuyu.lifefunniments.LifeFunnimentsPlugin.Companion.mm
import love.chihuyu.timerapi.TimerAPI
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component

object FishingBattleCommand: Command {

    override fun register() {
        commandAPICommand("fishingbattle") {
            withPermission("lifefunniments.fishingbattle")
            subcommand("start") {
                withPermission("lifefunniments.fishingbattle.start")
                playerExecutor { player, _ ->
                    if (FishingStorage.datas.any { it.owner == player }) {
                        player.sendMessage(Component.text("あなたは既に釣りバトルを開始しています！"))
                    }

                    val fishingBattle = FishingBattle(player)

                    TimerAPI.run("fishbattle-${player.name}", 180, 20) {
                        start {
                            LifeFunnimentsPlugin.server.broadcast(mm.deserialize("""
                                <blue><strikethrough>${" ".repeat(60)}</blue>
                                
                                <light_purple><bold><italic>釣りバトルが開始されました！</light_purple>
                                <white>三分間で一番多くサーバー内で魚を釣った人が勝ちです！</white>
                                
                                <blue><strikethrough>${" ".repeat(60)}</blue>
                            """.trimIndent()))

                            FishingStorage.datas += fishingBattle
                            LifeFunnimentsPlugin.server.onlinePlayers.forEach {
                                it.playSound(Sound.sound(Key.key("entity.ender_dragon.ambient"), Sound.Source.AMBIENT, .7f, 1f))
                            }
                        }
                        end {
                            LifeFunnimentsPlugin.server.broadcast(mm.deserialize("""
                                <blue><strikethrough>${" ".repeat(60)}</blue>
                                
                                <light_purple><bold><italic>釣りバトルが終了しました！
                                勝者は<white><bold><italic>${fishingBattle.fished.maxWith { _, int -> int.value }.key.name}<light_purple><bold><italic>です！</light_purple>
                                
                                <blue><strikethrough>${" ".repeat(60)}</blue>
                            """.trimIndent()))

                            FishingStorage.datas -= fishingBattle
                            LifeFunnimentsPlugin.server.onlinePlayers.forEach {
                                it.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.AMBIENT, 1f, 1f))
                            }
                        }
                    }
                }
            }
        }
    }
}