package com.udacity.pawhaven

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Connects this Activity to activity_main.xml
        setContentView(R.layout.activity_main)

        // Find the Get Started button
        setUpOnGetStartedButtonClicked()

        Toast.makeText(this, R.string.welcome_message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpOnGetStartedButtonClicked() {
        val getStartedBtn: Button = findViewById(R.id.get_started_btn)

        getStartedBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

}
