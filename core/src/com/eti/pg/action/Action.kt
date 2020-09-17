package com.eti.pg.action

import com.eti.pg.actor.Actor

interface Action {
    fun perform() = Unit
}