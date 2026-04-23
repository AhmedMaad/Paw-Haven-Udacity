package com.udacity.pawhaven

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
