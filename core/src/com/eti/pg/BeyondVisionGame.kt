package com.eti.pg

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2


class BeyondVisionGame : ApplicationAdapter() {
    var batch: SpriteBatch? = null
    var logo: Texture? = null
    var batchShape: ShapeRenderer? = null
    var cam: OrthographicCamera? = null
    var time = 0.0
    val h = 1920f
    val w = 1080f
    val r = 20f
    var posCenter: Vector2? = null


    override fun create() {
        batch = SpriteBatch()
        batchShape = ShapeRenderer()
        cam = OrthographicCamera()
        cam!!.setToOrtho(false, w, h)
        batchShape!!.setProjectionMatrix(cam!!.combined)
        logo = Texture("badlogic.jpg")
        posCenter = Vector2(w/2, h/2)
    }

    override fun render() {
        time += 0.01f
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glClearColor(0.15f, 0.14f, 0.11f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        cam!!.update()

        batchShape!!.begin(ShapeRenderer.ShapeType.Filled)
        drawParticles(9, 10f)
        batchShape!!.end()

        batch!!.enableBlending();

        batch!!.begin()
        batch!!.draw(logo, 0f, 0f)
        batch!!.end()

        if (Gdx.input.isTouched) {
            println(" X " + Gdx.input.x
                    * (w / Gdx.app.graphics.width))
            println(" Y " + Gdx.input.y
                    * (h / Gdx.app.graphics.height))
        }

    }

    override fun dispose() {
        batch!!.dispose()
        logo!!.dispose()
    }

    private fun drawParticles(particleCount: Int, particleSize: Float) {
        for (i in 1..particleCount) {
            batchShape?.setColor(1f - i / 5f, 1f * i / 5f, i / 5f, (-0.3 + time).toFloat())
            batchShape?.circle(
                    (-Math.sin(time * i / 2f) * r * i + posCenter!!.x).toFloat(),
                    (Math.cos(time * i / 2f) * r * i / 2f + posCenter!!.y / 2f +
                            Interpolation.exp5.apply((time * (i + 1f)).toFloat()) * posCenter!!.y +
                            Math.sin(time) * h / 20f).toFloat(),
                    particleSize)
        }
    }

}