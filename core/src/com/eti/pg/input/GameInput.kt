package com.eti.pg.input

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.input.GestureDetector
import com.eti.pg.BeyondVisionGame
import com.eti.pg.action.WalkAction
import com.eti.pg.screen.GameScreen
import com.eti.pg.screen.MenuScreen
import com.eti.pg.screen.SplashScreen
import ktx.log.debug
import kotlin.math.sqrt
import ktx.log.logger
import kotlin.math.abs

private val LOG = logger<GestureInput>()

enum class Directions(val direction: Int){
    X(0), Y(1)
}

class SimpleInput(val game: BeyondVisionGame) : InputAdapter(){
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if(game.shownScreen !is SplashScreen){
            return false
        }
        game.setScreen<GameScreen>()
        return true
    }
}

class GestureInput(val game: BeyondVisionGame) : GestureDetector.GestureAdapter(){

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        val direction = if(abs(velocityX) > abs(velocityY)) Directions.X else Directions.Y
        val direction2: Int
        if(direction == Directions.X){
            direction2 = if(velocityX>0) 1 else -1
            WalkAction(game.getScreen<GameScreen>().player, direction2, 0).perform()
        }else{
            direction2 = if(velocityY>0) 1 else -1
            WalkAction(game.getScreen<GameScreen>().player,0, direction2).perform()
        }

        LOG.debug { "FLING" }

        return true
    }
}