package com.eti.pg

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.eti.pg.screen.BeyondVisionScreen
import com.eti.pg.screen.GameScreen
import com.eti.pg.screen.MenuScreen
import com.eti.pg.screen.SplashScreen
import ktx.app.KtxGame
import ktx.log.debug
import ktx.log.logger

const val VIRTUAL_WIDTH = 9f
const val VIRTUAL_HEIGHT = 21f
const val TEXT_SCALE = 0.03f

class BeyondVisionGame : KtxGame<BeyondVisionScreen>() {
    companion object {
        val log = logger<BeyondVisionGame>()
    }

    val batch : Batch by lazy { SpriteBatch(20) }
    val viewPort : Viewport = FillViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT)

    override fun create() {
        addScreen(SplashScreen(this))
        addScreen(MenuScreen(this))
        addScreen(GameScreen(this))
        setScreen<SplashScreen>()

        log.debug { "Game instance running properly" }
    }

    override fun resize(width: Int, height: Int) {
        viewPort.update(width, height, true)
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
    }
}