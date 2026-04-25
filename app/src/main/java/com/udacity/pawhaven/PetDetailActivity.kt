package com.udacity.pawhaven

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.udacity.pawhaven.data.IntentExtras

class PetDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pet_detail)

        val receivedPetID = intent.getStringExtra(IntentExtras.EXTRA_PET_ID)

        if (receivedPetID != null) {
            val data = Bundle()
            data.putString(IntentExtras.EXTRA_PET_ID, receivedPetID)

            val detailFragment = PetDetailFragment()
            detailFragment.setArguments(data)

            supportFragmentManager
                .beginTransaction()
                .add(R.id.detailContainer, detailFragment)
                .commit()
        }
    }
}