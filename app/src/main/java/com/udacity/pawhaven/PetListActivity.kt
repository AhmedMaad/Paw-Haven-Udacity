package com.udacity.pawhaven

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.IntentExtras

class PetListActivity : BaseActivity(), PetListFragment.Host {

    private val handleAnimalData =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //TODO: Handle received Data
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pet_list_activity)

        val data = Bundle()
        data.putBoolean(IntentExtras.EXTRA_TWO_PANE, isTwoPane())

        val listFragment = PetListFragment()
        listFragment.setArguments(data)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.listContainer, listFragment)
            .commit()
    }

    override fun onPetSelected(pet: Animal) {
        if (isTwoPane()) {

            val data = Bundle()
            data.putParcelable(IntentExtras.EXTRA_PET, pet)

            val detailFragment = PetDetailFragment()
            detailFragment.setArguments(data)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.detailContainer, detailFragment)
                .commit()
        } else {
            val i = Intent(this, PetDetailActivity::class.java)
            i.putExtra(IntentExtras.EXTRA_PET, pet)
            startActivity(i)
        }
    }

    override fun onAddClicked() {
        val intent = Intent(this, AddAnimalActivity::class.java)
        handleAnimalData.launch(intent)
        //TODO: This intent returns an animal that will be then added to Repository.pets
        //Volunteers can quickly add new animals to the list.
    }

    override fun isTwoPane(): Boolean {
        return findViewById<View?>(R.id.detailContainer) != null
    }

}