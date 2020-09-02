package com.eti.pg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.eti.pg.BeyondVisionGame
import ktx.graphics.use
import ktx.log.debug
import ktx.log.logger

private val LOG = logger<SplashScreen>()

class SplashScreen(beyondVisionGame: BeyondVisionGame) : BeyondVisionScreen(beyondVisionGame) {
    val title = Texture("title.jpg")
    val shapeRenderer = ShapeRenderer()
    val cam = OrthographicCamera()
    val h = 1920f
    val w = 1080f
    val posCenter = Vector2(w/2, h/2)
    val r = 20f
    var time = 0.0

    override fun show() {
        LOG.debug { "Splash screen is shown" }

        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glClearColor(0.15f, 0.14f, 0.11f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam.setToOrtho(false, w, h)
        shapeRenderer.projectionMatrix = cam.combined
        batch.enableBlending()
    }

    override fun render(delta: Float) {
        time += delta

        cam.update()

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        drawParticles(9, 10f)
        shapeRenderer.end()

        batch.use {
            it.draw(title, 0f, 0f)
        }

        if (Gdx.input.isTouched) beyondVisionGame.setScreen<MenuScreen>()
    }

    private fun drawParticles(particleCount: Int, particleSize: Float) {
        for (i in 1..particleCount) {
            shapeRenderer.setColor(1f - i / 5f, 1f * i / 5f, i / 5f, (-0.3 + time).toFloat())
            shapeRenderer.circle(
                    (-Math.sin(time * i / 2f) * r * i + posCenter.x).toFloat(),
                    (Math.cos(time * i / 2f) * r * i / 2f + posCenter.y / 2f +
                            Interpolation.exp5.apply((time * (i + 1f)).toFloat()) * posCenter.y +
                            Math.sin(time) * h / 20f).toFloat(),
                    particleSize)
        }
    }

    override fun dispose() {
        batch.dispose()
        title.dispose()
        shapeRenderer.dispose()
    }
}