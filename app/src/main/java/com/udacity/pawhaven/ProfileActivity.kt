package com.udacity.pawhaven

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.udacity.pawhaven.data.Person
import com.udacity.pawhaven.data.Repository
import com.udacity.pawhaven.data.Role
import com.udacity.pawhaven.data.newInterestedParent
import com.udacity.pawhaven.data.newVolunteer
import kotlin.text.toInt

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setUpOnEnterButtonClicked()
    }

    private fun setUpOnEnterButtonClicked() {
        val enterBtn: Button = findViewById(R.id.enter_btn)
        val firstNameET: EditText = findViewById(R.id.first_name_et)
        val lastNameET: EditText = findViewById(R.id.last_name_et)
        val ageET: EditText = findViewById(R.id.age_et)
        val roleRG: RadioGroup = findViewById(R.id.role_rg)

        enterBtn.setOnClickListener {
            val firstName = firstNameET.text.toString()
            val lastName = lastNameET.text.toString()
            val age = ageET.text.toString()

            val areFieldsValidated = validateFields(firstName, lastName, age)
            if (areFieldsValidated) {

                val checkedRB = roleRG.checkedRadioButtonId
                when (checkedRB) {
                    R.id.interested_parent_rb -> {
                        Repository.user = newInterestedParent(firstName, lastName, age.toInt())
                        openPetListActivity()
                    }

                    R.id.volunteer_rb -> handleVolunteerChoice(firstName, lastName, age.toInt())
                }
            }

        }
    }

    private fun validateFields(firstName: String, lastName: String, age: String): Boolean {
        return if (firstName.isBlank() || lastName.isBlank() || age.isBlank()) {
            Toast.makeText(this, R.string.error_required, Toast.LENGTH_SHORT).show()
            false
        } else if (Regex("[0-9]").containsMatchIn(firstName) || Regex("[0-9]").containsMatchIn(
                lastName
            )
        ) {
            Toast.makeText(this, R.string.error_name_text, Toast.LENGTH_SHORT).show()
            false
        } else if (!age.isDigitsOnly()) {
            Toast.makeText(this, R.string.error_age_number, Toast.LENGTH_SHORT).show()
            false
        } else if (age.toInt() !in 10..50) {
            Toast.makeText(this, getString(R.string.error_age_range, 10, 50), Toast.LENGTH_SHORT)
                .show()
            false
        } else
            true
    }

    private fun handleVolunteerChoice(firstName: String, lastName: String, age: Int) {
        val volunteer = newVolunteer(firstName, lastName, age)
        val isFound = Repository.validVolunteers.contains(volunteer)
        if (isFound) {
            Repository.user = volunteer
            openPetListActivity()
        } else
            Toast.makeText(this, R.string.no_volunteer_match, Toast.LENGTH_SHORT)
                .show()
    }

    private fun openPetListActivity() {
        val intent = Intent(this, PetListActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

}