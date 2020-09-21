package com.eti.pg.action

import com.eti.pg.actor.Actor
import com.eti.pg.screen.GameScreen
import com.eti.pg.sounds.SoundManager
import ktx.log.debug
import ktx.log.logger

private val LOG = logger<GameScreen>()

class WalkAction(private val actor: Actor, private val x: Int, private val y: Int) : Action {

    override fun perform() {
        if(isMoveValid()){
            actor.position = Pair(actor.position.first + x, actor.position.second + y)
            SoundManager.walkSound.play()
            LOG.debug{"Player is position ${actor.position.first} ${actor.position.second}"}
        }
    }

    private fun isMoveValid() : Boolean{
        return (x + actor.position.first < actor.stage.size.first) and (x + actor.position.first >= 0) and (y + actor.position.second < actor.stage.size.second) and (y + actor.position.second >= 0)
    }
}