package com.udacity.pawhaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.Dog
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
        setUpAdoptAPawButton(view)

        val animalIV: ImageView = view.findViewById(R.id.animal_iv)
        val animalNameTV: TextView = view.findViewById(R.id.animal_name_tv)
        val animalAgeTV: TextView = view.findViewById(R.id.animal_age_tv)
        val shareIB: ImageButton = view.findViewById(R.id.share_ib)

        val audioView: View = view.findViewById(R.id.play_pause_view)
        val audioIcon: ImageView = audioView.findViewById(R.id.icon)

        val pet: Animal = Dog("", 12) //this pet will be received as parcelize
        animalIV.setImageResource(pet.imageRes)
        animalNameTV.text = pet.name
        animalAgeTV.text = getString(R.string.age_years_format, pet.age)

        /*shareIB.setOnClickListener{

        }*/

    }

    fun setUpAdoptAPawButton(view: View) {

        val adoptPawBtn: Button = view.findViewById(R.id.adopt_a_paw_btn)

        if (Repository.user?.role == Role.INTERESTED_PARENT) {
            adoptPawBtn.isVisible = true
            adoptPawBtn.setOnClickListener {
                Toast.makeText(context, R.string.adoption_coming_soon, Toast.LENGTH_SHORT).show()
            }
        }

    }

}