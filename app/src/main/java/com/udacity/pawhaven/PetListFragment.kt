package com.udacity.pawhaven

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.pawhaven.components.PetRowComponent
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.IntentExtras
import com.udacity.pawhaven.data.Repository
import com.udacity.pawhaven.data.Role

class PetListFragment : Fragment() {

    private var host: Host? = null

    //Reference: https://stackoverflow.com/a/14440308/10413818
    override fun onAttach(context: Context) {
        super.onAttach(context)
        host = context as Host
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAnimalsList(view)
    }

    private fun showAnimalsList(view: View) {
        val isTwoPane = requireArguments().getBoolean(IntentExtras.EXTRA_TWO_PANE, false)

        val animalsContainer: LinearLayout = view.findViewById(R.id.animals_container)
        animalsContainer.removeAllViews()

        for (pet in Repository.pets) {
            val petRow = PetRowComponent(requireContext())

            animalsContainer.addView(petRow)

            petRow.bind(pet, isTwoPane)

            petRow.setOnClickListener {
                host?.onPetSelected(pet)
            }

        }

        if (Repository.user?.role == Role.VOLUNTEER) {
            val addFAB: FloatingActionButton = view.findViewById(R.id.add_fab)
            addFAB.isVisible = true
            addFAB.setOnClickListener {
                host?.onAddClicked()
            }
        }
    }

    interface Host {
        fun onPetSelected(pet: Animal)
        fun onAddClicked()
        fun isTwoPane(): Boolean
    }

    fun refreshPetList() {
        showAnimalsList(requireView())
    }

}

