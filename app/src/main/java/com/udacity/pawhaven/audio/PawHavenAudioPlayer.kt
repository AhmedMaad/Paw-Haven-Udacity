package com.udacity.pawhaven.audio

import androidx.annotation.RawRes

interface PawHavenAudioPlayer {
    fun play(@RawRes soundRes: Int, onComplete: () -> Unit)
    fun stop()
    fun release()
}