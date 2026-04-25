package com.udacity.pawhaven

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.core.view.isVisible
import com.udacity.pawhaven.components.PlayPauseComponent
import com.udacity.pawhaven.data.AnimalType
import com.udacity.pawhaven.data.IntentExtras

class AddAnimalActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_animal)

        setUpOnBackButtonClicked()
        setUpOnSoundTextClicked()
        setUpOnTypeAutoCompleteSelected()
        setUpOnAddButtonClicked()

        //Reference: https://stackoverflow.com/a/78858366/10413818
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setResult(RESULT_CANCELED)
                finish()
            }
        })

    }

    private fun setUpOnAddButtonClicked() {
        val addBtn: Button = findViewById(R.id.add_btn)

        addBtn.setOnClickListener {
            val typeACTV: AutoCompleteTextView = findViewById(R.id.animal_type_actv)
            val nameET: TextView = findViewById(R.id.name_et)
            val ageET: TextView = findViewById(R.id.age_et)

            val typeString = typeACTV.text.toString()
            val name = nameET.text.toString()
            val age = ageET.text.toString()

            if (name.isBlank() || age.isBlank() || typeString.isBlank())
                Toast.makeText(this, R.string.error_required, Toast.LENGTH_SHORT).show()
            else {
                val animalType = when (typeString) {
                    "Dog" -> AnimalType.DOG
                    "Cat" -> AnimalType.CAT
                    "Parrot" -> AnimalType.PARROT
                    "Elephant" -> AnimalType.ELEPHANT
                    "Lion" -> AnimalType.LION
                    else -> AnimalType.BIRD
                }

                val animal = animalType.createAnimal(name, age.toInt())
                val intent = Intent()
                intent.putExtra(IntentExtras.EXTRA_PET, animal)
                setResult(RESULT_OK, intent)
                finish()
            }

        }
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
        val audioComponent: PlayPauseComponent = findViewById(R.id.play_pause_view)


        if (!audioComponent.isVisible)
            audioComponent.isVisible = true
        audioComponent.setSound(soundRes)
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
            setResult(RESULT_CANCELED)
            finish()
        }
    }

}