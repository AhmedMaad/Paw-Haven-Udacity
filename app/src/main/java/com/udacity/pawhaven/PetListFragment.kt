package com.udacity.pawhaven

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RawRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.pawhaven.audio.PawHavenAudioPlayer
import com.udacity.pawhaven.audio.PawHavenAudioPlayerImpl
import com.udacity.pawhaven.components.PetRowComponent
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.Repository
import com.udacity.pawhaven.data.Role


class PetListFragment : Fragment() {

    private lateinit var player: PawHavenAudioPlayer
    var playingIcon: ImageView? = null

    private val handleAnimalData =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //TODO: Handle received Data
        }

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
            val petRow = PetRowComponent(requireContext())

            animalsContainer.addView(petRow)
            petRow.bind(pet) { icon, soundRes ->
                handleAudio(icon, soundRes)
            }

            /*petRow.setOnClickListener {
                //pass data to Host...
            }*/

        }

        if (Repository.user?.role == Role.VOLUNTEER) {
            val addFAB: FloatingActionButton = view.findViewById(R.id.add_fab)
            addFAB.isVisible = true
            addFAB.setOnClickListener {
                val intent = Intent(requireContext(), AddAnimalActivity::class.java)
                handleAnimalData.launch(intent)
            }
        }


    }

    private fun handleAudio(audioIcon: ImageView, @RawRes audio: Int) {
        if (playingIcon != audioIcon) {
            //Handling diff. audio row selection
            playingIcon?.setImageResource(R.drawable.ic_play)

            audioIcon.setImageResource(R.drawable.ic_pause)
            player.play(audio) {
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

    //Defines PetListFragment.Host interface
    // to help PetListFragment communicate with the parent activity:
    interface Host {
        //TODO: Navigates to PetDetailActivity on phone
        //TODO: Shows PetDetailFragment on tablet, on the right
        fun onPetSelected(pet: Animal)

        //TODO: When fab button is clicked, navigates to AddAnimalActivity
        //TODO: This intent returns an animal that will be then added to Repository.pets
        //Volunteers can quickly add new animals to the list.
        fun onAddClicked()

        fun isTwoPane(): Boolean
    }

    //TODO: The PetListFragment should expose a method refreshPetList, to allow the list to be updated.

}

