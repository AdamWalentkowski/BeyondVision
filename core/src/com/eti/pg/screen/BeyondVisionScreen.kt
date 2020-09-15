package com.eti.pg.screen

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.utils.viewport.Viewport
import com.eti.pg.BeyondVisionGame
import ktx.app.KtxScreen

abstract class BeyondVisionScreen(
        val beyondVisionGame: BeyondVisionGame,
        val batch: Batch = beyondVisionGame.batch,
        val viewport: Viewport = beyondVisionGame.viewPort
) : KtxScreen