package com.eti.pg.actor

import com.eti.pg.Stage

interface Actor {
    val position: Pair<Int, Int>
    var stage: Stage
}