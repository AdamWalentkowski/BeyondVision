package com.eti.pg.input

import com.badlogic.gdx.InputAdapter
import com.eti.pg.BeyondVisionGame
import com.eti.pg.screen.MenuScreen
import com.eti.pg.screen.SplashScreen

class GameInput(val game: BeyondVisionGame) : InputAdapter(){

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        game.setScreen<MenuScreen>()
        return true
    }
}