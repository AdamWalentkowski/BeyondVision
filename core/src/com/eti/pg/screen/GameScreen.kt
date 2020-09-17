package com.eti.pg.screen

import com.badlogic.gdx.Gdx
import com.eti.pg.BeyondVisionGame
import com.eti.pg.actor.Actor
import com.eti.pg.actor.Player
import ktx.log.debug
import ktx.log.logger

private val LOG = logger<GameScreen>()

class GameScreen(beyondVisionGame: BeyondVisionGame) : BeyondVisionScreen(beyondVisionGame) {
    val player = Player()
    val walkSound = Gdx.audio.newSound(Gdx.files.internal("minecraft_walk.mp3"))
    val actors: List<Actor> = listOf(player)

    override fun show() {
        LOG.debug { "Menu screen is shown" }
        player
    }

    fun movePlayer(x: Int, y: Int){

        if((x + player.position.first < player.stage.size.first) and (x + player.position.first >= 0) and (y + player.position.second < player.stage.size.second) and (y + player.position.second >= 0)){
            player.position = Pair(player.position.first+x, player.position.second+y)
            walkSound.play()
        }

        LOG.debug{"Player is position ${player.position.first} ${player.position.second}"}
    }

    override fun render(delta: Float) {
        actors.forEach{

        }
    }
}