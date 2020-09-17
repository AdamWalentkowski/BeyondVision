package com.eti.pg.input

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.input.GestureDetector
import com.eti.pg.BeyondVisionGame
import com.eti.pg.action.WalkAction
import com.eti.pg.screen.GameScreen
import com.eti.pg.screen.SplashScreen
import ktx.log.debug
import ktx.log.logger
import kotlin.math.abs
import kotlin.math.sign

class SplashScreenInput(private val game: BeyondVisionGame) : InputAdapter(){
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if(game.shownScreen !is SplashScreen){
            return false
        }
        game.setScreen<GameScreen>()
        return true
    }
}

class MenuScreenInput(private val game: BeyondVisionGame) : GestureDetector.GestureAdapter(){
    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        TODO("Add double tap handling")
    }
}

class GameScreenInput(private val game: BeyondVisionGame) : GestureDetector.GestureAdapter(){
    companion object {
        val log = logger<GameScreenInput>()
    }

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        if(game.shownScreen !is GameScreen){
            return false
        }
        var directionX = 0
        var directionY = 0
        when {
            abs(velocityX) > abs(velocityY) -> directionX = sign(velocityX).toInt()
            else -> directionY = sign(velocityY).toInt()
        }
        WalkAction(game.getScreen<GameScreen>().player, directionX, directionY).perform()
        log.debug { "Fling x:${directionX} y:${directionY}" }
        return true
    }
}