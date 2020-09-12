package com.eti.pg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Interpolation
import com.eti.pg.BeyondVisionGame
import com.eti.pg.VIRTUAL_HEIGHT
import com.eti.pg.VIRTUAL_WIDTH
import ktx.graphics.use
import ktx.log.debug
import ktx.log.logger
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private val LOG = logger<SplashScreen>()

class SplashScreen(beyondVisionGame: BeyondVisionGame) : BeyondVisionScreen(beyondVisionGame) {
    private val title = Texture("title.jpg")
    private val shapeRenderer = ShapeRenderer()
    private val colorSequence = (1..10).map { Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f) }
    private var time = 0f

        override fun show() {
        LOG.debug { "Splash screen is shown" }
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glClearColor(0.15f, 0.14f, 0.11f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.enableBlending()
    }

    override fun render(delta: Float) {
        time += delta

        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            drawParticles()
        }

        batch.use {
            it.draw(title, 0f, 0f)
        }

        if (Gdx.input.isTouched) beyondVisionGame.setScreen<MenuScreen>()
    }

    private fun drawParticles() {
        colorSequence.forEachIndexed { i, color ->
            shapeRenderer.color = color
            shapeRenderer.circle(
                    -sin(time * i / 2.0f) * 20 * i + VIRTUAL_WIDTH / 2f,
                    cos(time * i / 2.0f) * 10 * i + VIRTUAL_WIDTH / 4f + Interpolation.exp5.apply(time * i) * VIRTUAL_HEIGHT / 40f + sin(time) * 20,
                    10f
            )
        }
    }

    override fun dispose() {
        batch.dispose()
        title.dispose()
        shapeRenderer.dispose()
    }
}