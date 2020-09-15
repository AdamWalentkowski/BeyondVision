package com.eti.pg.action

import com.eti.pg.actor.Actor

abstract class Action(val actor: Actor) {
    open fun perform() = Unit
}