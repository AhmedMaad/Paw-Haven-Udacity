package com.udacity.pawhaven.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.RawRes
import com.udacity.pawhaven.R
import com.udacity.pawhaven.audio.PawHavenAudioPlayer
import com.udacity.pawhaven.audio.PawHavenAudioPlayerImpl

class PlayPauseComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    companion object {
        private var activeComponent: PlayPauseComponent? = null
    }

    private val icon: ImageView

    private var isPlaying: Boolean = false
    private var soundResId: Int = 0
    private val audioPlayer: PawHavenAudioPlayer


    init {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.play_pause_view, this, true)

        audioPlayer = PawHavenAudioPlayerImpl.getInstance(context)
        icon = view.findViewById(R.id.icon)

        isClickable = true
        isFocusable = true

        setOnClickListener { toggle() }
        updateIcon()
    }

    /**
     * Sets the sound this component should preview.
     * If a sound was playing, it is stopped.
     */
    fun setSound(@RawRes soundResId: Int) {
        forceStop()
        this.soundResId = soundResId
        updateIcon()
    }

    private fun forceStop() {
        if (!isPlaying) return

        audioPlayer.stop()
        isPlaying = false
        if (activeComponent == this) {
            activeComponent = null
        }
        updateIcon()
    }

    private fun toggle() {
        val player = audioPlayer
        if (soundResId == 0) return

        if (!isPlaying) {
            // Stop any other active component
            activeComponent?.forceStop()

            activeComponent = this

            isPlaying = true
            updateIcon()

            player.play(soundResId) {
                // Sound finished
                isPlaying = false
                updateIcon()
            }
        } else {
            player.stop()
            isPlaying = false
            updateIcon()
        }
    }

    private fun updateIcon() {
        if (isPlaying) {
            icon.setImageResource(R.drawable.ic_pause)
            icon.contentDescription =
                context.getString(R.string.pause_sound)
        } else {
            icon.setImageResource(R.drawable.ic_play)
            icon.contentDescription =
                context.getString(R.string.play_sound)
        }
    }
}
