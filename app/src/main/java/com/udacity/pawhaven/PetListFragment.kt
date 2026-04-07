package com.udacity.pawhaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.udacity.pawhaven.data.Repository


class PetListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_pet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animalsContainer: LinearLayout = view.findViewById(R.id.animals_container)

        for (pet in Repository.pets) {
            val animalView = layoutInflater.inflate(R.layout.view_pet_row, animalsContainer, false)

            val animalIV: ImageView = animalView.findViewById(R.id.petImage)
            animalIV.setImageResource(pet.imageRes)

            animalsContainer.addView(animalView)
        }
    }

}