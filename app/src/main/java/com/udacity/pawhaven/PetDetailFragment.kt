package com.udacity.pawhaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import com.udacity.pawhaven.data.Repository
import com.udacity.pawhaven.data.Role

class PetDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pet_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdoptAPawButton(view)

    }

    fun setUpAdoptAPawButton(view: View) {

        val adoptPawBtn: Button = view.findViewById(R.id.adopt_a_paw_btn)

        if (Repository.user?.role == Role.INTERESTED_PARENT) {
            adoptPawBtn.isVisible = true
            adoptPawBtn.setOnClickListener {
                Toast.makeText(context, R.string.adoption_coming_soon, Toast.LENGTH_SHORT).show()
            }
        }

    }

}