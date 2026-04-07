package com.udacity.pawhaven.audio


import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

class PawHavenAudioPlayerImpl(
    private val context: Context
) : PawHavenAudioPlayer {

    private var mp: MediaPlayer? = null

    override fun play(@RawRes soundRes: Int, onComplete: () -> Unit) {
        stop()

        mp = MediaPlayer.create(context, soundRes)?.apply {
            setOnCompletionListener {
                stop()
                onComplete()
            }
            start()
        }
    }

    override fun stop() {
        mp?.release()
        mp = null
    }

    override fun release() {
        stop()
    }

    companion object {
        private var instance : PawHavenAudioPlayer? = null
        fun getInstance(context: Context) : PawHavenAudioPlayer {
            instance = instance ?: PawHavenAudioPlayerImpl(context)
            return instance!!
        }
    }
}

