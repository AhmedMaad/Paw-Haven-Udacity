package com.udacity.pawhaven

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PetListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pet_list_activity)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.listContainer, PetListFragment())
            .commit()

    }
}