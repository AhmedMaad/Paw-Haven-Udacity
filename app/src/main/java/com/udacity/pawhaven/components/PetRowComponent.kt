package com.udacity.pawhaven.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
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
    private var audioComponent: PlayPauseComponent

    init {
        orientation = VERTICAL

        val view = LayoutInflater.from(context).inflate(R.layout.view_pet_row, this, true)

        animalIV = view.findViewById(R.id.petImage)
        animalNameTV = view.findViewById(R.id.petName)
        animalAgeTV = view.findViewById(R.id.petAge)
        audioComponent = view.findViewById(R.id.play_pause_view)
    }

    fun bind(pet: Animal, isTwoPane: Boolean) {
        animalIV.setImageResource(pet.imageRes)
        animalNameTV.text = pet.name
        animalAgeTV.text = context.getString(R.string.age_years_format, pet.age)
        if (isTwoPane)
            audioComponent.isVisible = false
        else
            audioComponent.setSound(pet.soundRes)
    }

}