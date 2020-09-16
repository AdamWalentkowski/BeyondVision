package com.eti.pg.actor

import com.eti.pg.Stage

class Player: Actor {
    override var position = Pair(0, 0)
    override var stage = Stage(id = 0, size = Pair(2, 4), actors = listOf(this), exits = mapOf())
}