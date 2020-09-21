package com.eti.pg.action

import com.eti.pg.actor.Actor
import com.eti.pg.screen.GameScreen
import com.eti.pg.sounds.SoundManager
import ktx.log.debug
import ktx.log.logger

class WalkAction(private val actor: Actor, private val x: Int, private val y: Int) : Action {
    companion object {
        val log = logger<WalkAction>()
    }

    override fun perform() {
        if(isMoveValid()){
            actor.position = Pair(actor.position.first + x, actor.position.second + y)
            SoundManager.walkSound.play()
            log.debug{"Player is position ${actor.position.first} ${actor.position.second}"}
        }
    }

    private fun isMoveValid(): Boolean {
        return (x + actor.position.first in 0 until actor.stage.size.first) and
        (y + actor.position.second in 0 until actor.stage.size.second)
    }
}