package com.eti.pg.sounds

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound

object SoundManager{
    val walkSound: Sound = Gdx.audio.newSound(Gdx.files.internal("minecraft_walk.mp3"))
}