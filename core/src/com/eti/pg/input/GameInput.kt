package com.eti.pg.input

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.input.GestureDetector
import com.eti.pg.BeyondVisionGame
import com.eti.pg.action.WalkAction
import com.eti.pg.screen.GameScreen
import com.eti.pg.screen.MenuScreen
import ktx.log.debug
import ktx.log.logger
import kotlin.math.abs
import kotlin.math.sign

class SplashScreenInput(private val game: BeyondVisionGame) : InputAdapter(){
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        game.setScreen<MenuScreen>()
        return true
    }
}

class MenuScreenInput(private val game: BeyondVisionGame) : GestureDetector.GestureAdapter(){
    companion object {
        val log = logger<MenuScreenInput>()
    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        log.debug { "Tap count: $count" }
        return if (count == 2) {
            game.setScreen<GameScreen>()
            true
        }
        else false
    }
}

class GameScreenInput(private val game: BeyondVisionGame) : GestureDetector.GestureAdapter(){
    companion object {
        val log = logger<GameScreenInput>()
    }

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        var directionX = 0
        var directionY = 0
        when {
            abs(velocityX) > abs(velocityY) -> directionX = sign(velocityX).toInt()
            else -> directionY = sign(velocityY).toInt()
        }
        WalkAction(game.getScreen<GameScreen>().player, directionX, directionY).perform()
        log.debug { "Fling x: $directionX y:$directionY" }
        return true
    }
}