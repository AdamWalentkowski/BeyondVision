package com.eti.pg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Interpolation
import com.eti.pg.BeyondVisionGame
import com.eti.pg.TEXT_SCALE
import com.eti.pg.VIRTUAL_HEIGHT
import com.eti.pg.VIRTUAL_WIDTH
import com.eti.pg.input.SplashScreenInput
import ktx.graphics.use
import ktx.log.debug
import ktx.log.logger
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.random.Random

class SplashScreen(beyondVisionGame: BeyondVisionGame) : BeyondVisionScreen(beyondVisionGame) {
    companion object {
        val log = logger<SplashScreen>()
    }

    private var time = 0f
    private val title = Texture("title.jpg")
    private val font = BitmapFont()
    private val shapeRenderer = ShapeRenderer()
    private val colorSequence = (1..6).map {
        Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), -0.5f)
    }

    override fun show() {
        log.debug { "Splash screen is shown" }
        Gdx.input.inputProcessor = SplashScreenInput(beyondVisionGame)
    }

    override fun render(delta: Float) {
        time += delta

        Gdx.gl.glClearColor(0.15f, 0.14f, 0.11f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.use(viewport.camera.combined.cpy().scale(TEXT_SCALE, TEXT_SCALE, 1f)) {
            val glyphLayout = GlyphLayout(font, "Tap to play")
            font.color = Color(1f, 1f, 1f, sin(time))
            font.draw(it,
                    glyphLayout,
                    (VIRTUAL_WIDTH / TEXT_SCALE - glyphLayout.width) / 2f,
                    (VIRTUAL_HEIGHT / TEXT_SCALE - glyphLayout.height) / 4f)
        }

        batch.use(viewport.camera.combined) {
            it.enableBlending()
            it.setColor(1f, 1f, 1f, min(time / 2f, 1f))
            it.draw(title,
                    -VIRTUAL_WIDTH / 2f,
                    VIRTUAL_HEIGHT / 2f,
                    VIRTUAL_WIDTH * 2f,
                    VIRTUAL_HEIGHT / 3f)
        }

        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)

        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            shapeRenderer.projectionMatrix = viewport.camera.combined
            drawParticles(delta)
        }
    }

    private fun drawParticles(delta: Float) {
        colorSequence.forEachIndexed { i, color ->
            val horizontalCircularMotion = -sin(time * (i.inc() + 1) / 2.0f) + VIRTUAL_WIDTH / 2f
            val verticalCircularMotion = cos(time * i.inc() / 2.0f) + VIRTUAL_HEIGHT / 5f
            val fadeInMotion = Interpolation.exp5.apply(time * i.inc() / 10f) * VIRTUAL_HEIGHT / 5f
            val floatMotion = sin(time)
            color.a = min(color.a + delta / 2f, 1f)
            shapeRenderer.color = color
            shapeRenderer.circle(
                    horizontalCircularMotion,
                    verticalCircularMotion + fadeInMotion + floatMotion,
                    0.2f
            )
        }
    }

    override fun dispose() {
        batch.dispose()
        title.dispose()
        shapeRenderer.dispose()
    }
}