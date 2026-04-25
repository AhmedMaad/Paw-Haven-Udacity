package com.udacity.pawhaven

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.udacity.pawhaven.components.PlayPauseComponent
import com.udacity.pawhaven.data.IntentExtras
import com.udacity.pawhaven.data.Repository
import com.udacity.pawhaven.data.Role

class PetDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pet_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOnAdoptAPawButtonClicked(view)

        val receivedPetID = arguments?.getString(IntentExtras.EXTRA_PET_ID)

        if (receivedPetID != null) {

            val pet = Repository.getPetById(receivedPetID)

            if (pet != null) {
                val animalIV: ImageView = view.findViewById(R.id.animal_iv)
                val animalNameTV: TextView = view.findViewById(R.id.animal_name_tv)
                val animalAgeTV: TextView = view.findViewById(R.id.animal_age_tv)
                val descriptionTV: TextView = view.findViewById(R.id.description_tv)
                val shareIB: ImageButton = view.findViewById(R.id.share_ib)
                val audioComponent: PlayPauseComponent = view.findViewById(R.id.play_pause_view)

                animalIV.setImageResource(pet.imageRes)
                animalNameTV.text = pet.name
                animalAgeTV.text = getString(R.string.age_years_format, pet.age)
                descriptionTV.text = pet.description

                setUpOnShareImageButtonClicked(shareIB, pet.description)
                audioComponent.setSound(pet.soundRes)
            }

        }

    }

    private fun setUpOnShareImageButtonClicked(shareIB: ImageButton, description: String) {
        shareIB.setOnClickListener {
            ShareCompat
                .IntentBuilder(requireContext())
                .setType("text/plain")
                .setChooserTitle(getString(R.string.share_animal_with))
                .setText(description)
                .startChooser()
        }
    }

    fun setUpOnAdoptAPawButtonClicked(view: View) {

        val adoptPawBtn: Button = view.findViewById(R.id.adopt_a_paw_btn)

        if (Repository.user?.role == Role.INTERESTED_PARENT) {
            adoptPawBtn.isVisible = true
            adoptPawBtn.setOnClickListener {
                Toast.makeText(context, R.string.adoption_coming_soon, Toast.LENGTH_SHORT).show()
            }
        }

    }

}