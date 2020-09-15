package com.eti.pg

import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.badlogic.gdx.graphics.OrthographicCamera
import com.eti.pg.input.GameInput
import com.eti.pg.screen.BeyondVisionScreen
import com.eti.pg.screen.MenuScreen
import com.eti.pg.screen.SplashScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.log.debug
import ktx.log.logger

private val LOG = logger<BeyondVisionGame>()

val VIRTUAL_WIDTH = 9f
val VIRTUAL_HEIGHT = 21f
val TEXT_SCALE = 0.03f

class BeyondVisionGame : KtxGame<BeyondVisionScreen>() {
    val batch : Batch by lazy { SpriteBatch(20) }
    val viewPort : Viewport = FillViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT)

    override fun create() {
        Gdx.app.logLevel = LOG_DEBUG

        addScreen(SplashScreen(this))
        addScreen(MenuScreen(this))
        setScreen<SplashScreen>()

        Gdx.input.inputProcessor = GameInput(this)

        LOG.debug { "Game instance running properly" }
    }

    override fun resize(width: Int, height: Int) {
        viewPort.update(width, height, true)
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
    }
}