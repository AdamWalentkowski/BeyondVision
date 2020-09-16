package com.eti.pg.screen

import com.eti.pg.BeyondVisionGame
import com.eti.pg.actor.Player
import ktx.log.debug
import ktx.log.logger

private val LOG = logger<GameScreen>()

class GameScreen(beyondVisionGame: BeyondVisionGame) : BeyondVisionScreen(beyondVisionGame) {
    private val player = Player()

    override fun show() {
        LOG.debug { "Menu screen is shown" }
        player
    }



    override fun render(delta: Float) {

    }
}