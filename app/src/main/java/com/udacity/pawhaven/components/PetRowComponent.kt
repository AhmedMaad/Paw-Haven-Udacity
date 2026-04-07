package com.udacity.pawhaven.components

import android.content.Context
import android.opengl.Visibility
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.udacity.pawhaven.R
import com.udacity.pawhaven.data.Animal

class PetRowComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL

        // Inflate the row layout into this custom view
        LayoutInflater.from(context).inflate(R.layout.view_pet_row, this, true)

        //TODO Handle View Binding (findViewById) including the PlayPauseComponent

    }

    fun bind(pet: Animal) {
        // TODO:
        // - set image resource
        // - set name text
        // - set age text using age_years_format
        // - set sound on playButton (pet.soundRes)
    }
}