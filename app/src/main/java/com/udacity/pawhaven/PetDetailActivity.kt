package com.udacity.pawhaven

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.IntentExtras

class PetDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pet_detail)

        val receivedPet = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(IntentExtras.EXTRA_PET, Animal::class.java)
        else
            intent.getParcelableExtra(IntentExtras.EXTRA_PET)

        val data = Bundle()
        data.putParcelable(IntentExtras.EXTRA_PET, receivedPet)

        val detailFragment = PetDetailFragment()
        detailFragment.setArguments(data)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.detailContainer, detailFragment)
            .commit()

    }
}