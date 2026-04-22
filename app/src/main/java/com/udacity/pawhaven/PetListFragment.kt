package com.udacity.pawhaven

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RawRes
import androidx.fragment.app.Fragment
import com.udacity.pawhaven.audio.PawHavenAudioPlayer
import com.udacity.pawhaven.audio.PawHavenAudioPlayerImpl
import com.udacity.pawhaven.components.PetRowComponent
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
            val petRow = PetRowComponent(requireContext())

            animalsContainer.addView(petRow)
            petRow.bind(pet) { icon, soundRes ->
                handleAudio(icon, soundRes)
            }

            /*petRow.setOnClickListener {
                //pass data to Host...
            }*/

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
    interface Host {
        fun onPetSelected(pet: Animal)
        fun onAddClicked()
        fun isTwoPane(): Boolean
    }
}

