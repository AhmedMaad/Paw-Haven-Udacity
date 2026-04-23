package com.udacity.pawhaven

import android.os.Bundle

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