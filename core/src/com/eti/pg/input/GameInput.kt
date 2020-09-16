package com.eti.pg.input

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.input.GestureDetector
import com.eti.pg.BeyondVisionGame
import com.eti.pg.screen.GameScreen
import com.eti.pg.screen.MenuScreen
import com.eti.pg.screen.SplashScreen
import ktx.log.debug
import kotlin.math.sqrt
import ktx.log.logger
import kotlin.math.abs

private val LOG = logger<GestureInput>()

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
    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        val distance = sqrt(deltaX*deltaX + deltaY*deltaY)
        LOG.debug{" X: ${x} Y: ${y} Distance: ${distance} DeltaX: ${deltaX} DeltaY: ${deltaY}"}

        when {
//            abs(x) > abs(y) -> if x > 0 return game.getScreen
        }
        return true
    }
}