package com.eti.pg

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(BeyondVisionGame(), AndroidApplicationConfiguration().apply {
            useImmersiveMode = true
            hideStatusBar = true
            useGyroscope = true
            useWakelock = true
        })
    }
}