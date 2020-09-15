package com.eti.pg.input

import com.badlogic.gdx.InputAdapter

class GameInput : InputAdapter(){
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return super.touchDown(screenX, screenY, pointer, button)
    }
}