package com.eti.pg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.input.GestureDetector
import com.eti.pg.BeyondVisionGame
import com.eti.pg.actor.Actor
import com.eti.pg.actor.Player
import com.eti.pg.input.GameScreenInput
import ktx.log.debug
import ktx.log.logger

class GameScreen(beyondVisionGame: BeyondVisionGame) : BeyondVisionScreen(beyondVisionGame) {
    companion object {
        val log = logger<GameScreen>()
    }

    val player = Player()
//    private val walkSound: Sound = Gdx.audio.newSound(Gdx.files.internal("minecraft_walk.mp3"))
    private val actors: List<Actor> = listOf(player)

    override fun show() {
        log.debug { "Game screen is shown" }
        Gdx.input.inputProcessor = GestureDetector(GameScreenInput(beyondVisionGame))
    }

    override fun render(delta: Float) {
        actors.forEach{
            TODO("Action for all characters")
        }
    }
}