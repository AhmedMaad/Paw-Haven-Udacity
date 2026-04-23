package com.udacity.pawhaven

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.udacity.pawhaven.audio.PawHavenAudioPlayer
import com.udacity.pawhaven.audio.PawHavenAudioPlayerImpl
import com.udacity.pawhaven.data.AnimalType

class AddAnimalActivity : BaseActivity() {

    private lateinit var audio: PawHavenAudioPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_animal)

        val addBtn: Button = findViewById(R.id.add_btn)

        audio = PawHavenAudioPlayerImpl.getInstance(this)

        setUpOnBackButtonClicked()
        setUpOnSoundTextClicked()
        setUpOnTypeAutoCompleteSelected()

        addBtn.setOnClickListener {
            //Create an animal object of this type using AnimalType.createAnimal
            //Return this animal back to PetListActivity as an extra
        }

        //TODO: Animal picture and sound to be changed according to changing "Animal Type"
        //Updates preview image and sound. This preview image is provided as a default
        // in the AnimalType.defaultIconRes and AnimalType.defaultSoundRes

    }

    private fun setUpOnTypeAutoCompleteSelected() {
        val animalTypeACTV: AutoCompleteTextView = findViewById(R.id.animal_type_actv)
        val picIV: ImageView = findViewById(R.id.picture_iv)
        var soundRes: Int

        val availableAnimals = AnimalType.entries.map { it.label }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, availableAnimals)
        animalTypeACTV.setAdapter(adapter)

        animalTypeACTV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            soundRes = AnimalType.entries[position].defaultSoundRes

            picIV.setImageResource(AnimalType.entries[position].defaultIconRes)

            handleSound(soundRes)
        }


    }

    private fun handleSound(soundRes: Int) {

        //TODO: Use the play/pause view in the xml
        //TODO: Show pause button while playing the audio

        val soundIV: ImageView = findViewById(R.id.sound_iv)

        if (!soundIV.isVisible)
            soundIV.isVisible = true

        soundIV.setOnClickListener {
            audio.play(soundRes) {}
        }

    }

    private fun setUpOnSoundTextClicked() {
        val soundTV: TextView = findViewById(R.id.sound_tv)
        soundTV.setOnClickListener {
            Toast.makeText(this, R.string.sound_clicked_placeholder, Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpOnBackButtonClicked() {
        val backIV: ImageView = findViewById(R.id.back_iv)
        backIV.setOnClickListener {
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        audio.release()
    }

}