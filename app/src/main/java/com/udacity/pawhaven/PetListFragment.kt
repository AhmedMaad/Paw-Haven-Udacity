package com.udacity.pawhaven

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.udacity.pawhaven.audio.PawHavenAudioPlayer
import com.udacity.pawhaven.audio.PawHavenAudioPlayerImpl
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.Repository


class PetListFragment : Fragment() {

    private lateinit var player: PawHavenAudioPlayer
    var playingIcon: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player = PawHavenAudioPlayerImpl.getInstance(requireContext())

        val animalsContainer: LinearLayout = view.findViewById(R.id.animals_container)

        for (pet in Repository.pets) {
            val animalView = layoutInflater.inflate(R.layout.view_pet_row, animalsContainer, false)

            val animalIV: ImageView = animalView.findViewById(R.id.petImage)
            val animalNameTV: TextView = animalView.findViewById(R.id.petName)
            val animalAgeTV: TextView = animalView.findViewById(R.id.petAge)

            val audioView: View = animalView.findViewById(R.id.play_pause_view)
            val audioIcon: ImageView = audioView.findViewById(R.id.icon)

            animalIV.setImageResource(pet.imageRes)
            animalNameTV.text = pet.name
            animalAgeTV.text = getString(R.string.age_years_format, pet.age)

            animalView.setOnClickListener {
                //pass data to Host..
            }

            audioView.setOnClickListener {
                handleAudio(audioIcon, pet)
            }

            animalsContainer.addView(animalView)
        }

    }

    private fun handleAudio(audioIcon: ImageView, pet: Animal) {
        if (playingIcon != audioIcon) {
            //Handling diff. audio row selection
            playingIcon?.setImageResource(R.drawable.ic_play)

            audioIcon.setImageResource(R.drawable.ic_pause)
            player.play(pet.soundRes) {
                audioIcon.setImageResource(R.drawable.ic_play)
                playingIcon = null
            }

            playingIcon = audioIcon
        } else {
            audioIcon.setImageResource(R.drawable.ic_play)
            player.stop()
            playingIcon = null
        }
    }

    override fun onPause() {
        super.onPause()
        player.release()
    }

}

interface Host {
    fun onPetSelected(pet: Animal)
    fun onAddClicked()
    fun isTwoPane(): Boolean
}