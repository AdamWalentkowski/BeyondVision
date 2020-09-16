package com.eti.pg

import com.eti.pg.actor.Actor

data class Stage(
        val id: Int,
        val size: Pair<Int, Int>,
        val actors: List<Actor>,
        val exits: Map<Int, Pair<Int, Int>>
)