package com.eti.pg.actor

import com.eti.pg.Stage

class Player: Actor {
    override val position = Pair(0, 0)
    override var stage = Stage(id = 0, size = Pair(1, 4), actors = listOf(this), exits = mapOf())
}