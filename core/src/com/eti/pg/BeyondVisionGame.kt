package com.eti.pg

import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.eti.pg.input.GestureInput
import com.eti.pg.input.SimpleInput
import com.eti.pg.screen.BeyondVisionScreen
import com.eti.pg.screen.GameScreen
import com.eti.pg.screen.MenuScreen
import com.eti.pg.screen.SplashScreen
import ktx.app.KtxGame
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

        val multiplexer = InputMultiplexer()
        multiplexer.addProcessor(SimpleInput(this))
        multiplexer.addProcessor(GestureDetector(GestureInput(this)))
        Gdx.input.inputProcessor = multiplexer

        addScreen(SplashScreen(this))
        addScreen(MenuScreen(this))
        addScreen(GameScreen(this))
        setScreen<SplashScreen>()

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