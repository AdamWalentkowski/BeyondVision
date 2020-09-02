package com.eti.pg.screen

import com.badlogic.gdx.graphics.g2d.Batch
import com.eti.pg.BeyondVisionGame
import ktx.app.KtxScreen

abstract class BeyondVisionScreen(
        val beyondVisionGame : BeyondVisionGame,
        val batch : Batch = beyondVisionGame.batch
) : KtxScreen