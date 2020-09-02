package com.eti.pg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.eti.pg.BeyondVisionGame
import ktx.graphics.use
import ktx.log.debug
import ktx.log.logger

private val LOG = logger<MenuScreen>()

class MenuScreen(beyondVisionGame: BeyondVisionGame) : BeyondVisionScreen(beyondVisionGame) {
    val menuPhoto = Texture("menu-placeholder.jpg")

    override fun show() {
        LOG.debug { "Menu screen is shown" }
    }
    override fun render(delta: Float) {
        batch.use {
            it.draw(menuPhoto, 0f, 0f)
        }

        if (Gdx.input.isTouched) {
            println(" X: ${Gdx.input.x}")
            println(" Y: ${Gdx.input.y}")
        }
    }
}