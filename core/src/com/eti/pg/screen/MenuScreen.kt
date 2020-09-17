package com.eti.pg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.input.GestureDetector
import com.eti.pg.BeyondVisionGame
import com.eti.pg.VIRTUAL_HEIGHT
import com.eti.pg.VIRTUAL_WIDTH
import com.eti.pg.input.MenuScreenInput
import ktx.graphics.use
import ktx.log.debug
import ktx.log.logger

class MenuScreen(beyondVisionGame: BeyondVisionGame) : BeyondVisionScreen(beyondVisionGame) {
    companion object {
        val log = logger<MenuScreen>()
    }

    private val menuPhoto = Texture("menu-placeholder.jpg")

    override fun show() {
        log.debug { "Menu screen is shown" }
        Gdx.input.inputProcessor = GestureDetector(MenuScreenInput(beyondVisionGame))
    }

    override fun render(delta: Float) {
        batch.use {
            it.draw(menuPhoto,
                    VIRTUAL_WIDTH / 2f,
                    VIRTUAL_HEIGHT / 2f,
                    1f,
                    1f
            )
        }
    }
}