package com.udacity.pawhaven.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.udacity.pawhaven.R
import com.udacity.pawhaven.data.Animal

class PetRowComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var animalIV: ImageView
    private var animalNameTV: TextView
    private var animalAgeTV: TextView
    private var audioIcon: ImageView

    init {
        orientation = VERTICAL

        val view = LayoutInflater.from(context).inflate(R.layout.view_pet_row, this, true)

        animalIV = view.findViewById(R.id.petImage)
        animalNameTV = view.findViewById(R.id.petName)
        animalAgeTV = view.findViewById(R.id.petAge)

        val audioView: View = view.findViewById(R.id.play_pause_view)
        audioIcon = audioView.findViewById(R.id.icon)
    }

    fun bind(pet: Animal, isTwoPane: Boolean, onAudioClick: (ImageView, Int) -> Unit) {
        animalIV.setImageResource(pet.imageRes)
        animalNameTV.text = pet.name
        animalAgeTV.text = context.getString(R.string.age_years_format, pet.age)
        if (isTwoPane)
            audioIcon.isVisible = false
        else
            audioIcon.setOnClickListener {
                onAudioClick(audioIcon, pet.soundRes)
            }
    }

}