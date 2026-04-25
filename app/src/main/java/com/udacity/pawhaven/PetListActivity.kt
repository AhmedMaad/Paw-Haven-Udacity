package com.udacity.pawhaven

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.IntentExtras
import com.udacity.pawhaven.data.Repository

class PetListActivity : BaseActivity(), PetListFragment.Host {

    private val handleAnimalData =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val receivedPet = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    result.data!!.getParcelableExtra(IntentExtras.EXTRA_PET, Animal::class.java)
                else
                    result.data!!.getParcelableExtra(IntentExtras.EXTRA_PET)

                Repository.pets.add(receivedPet!!)

                val fragment =
                    supportFragmentManager.findFragmentById(R.id.listContainer) as PetListFragment

                fragment.refreshPetList()
            }

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
            data.putString(IntentExtras.EXTRA_PET_ID, pet.id)

            val detailFragment = PetDetailFragment()
            detailFragment.setArguments(data)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.detailContainer, detailFragment)
                .commit()
        } else {
            val i = Intent(this, PetDetailActivity::class.java)
            i.putExtra(IntentExtras.EXTRA_PET_ID, pet.id)
            startActivity(i)
        }
    }

    override fun onAddClicked() {
        val intent = Intent(this, AddAnimalActivity::class.java)
        handleAnimalData.launch(intent)
    }

    override fun isTwoPane(): Boolean {
        return findViewById<View?>(R.id.detailContainer) != null
    }

}