package com.udacity.pawhaven.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Person(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val role: Role
) : Parcelable

fun newInterestedParent(
    firstName: String,
    lastName: String,
    age: Int
) = Person(firstName, lastName, age, Role.INTERESTED_PARENT)

fun newVolunteer(
    firstName: String,
    lastName: String,
    age: Int
) = Person(firstName, lastName, age, Role.VOLUNTEER)

enum class Role {
    VOLUNTEER,
    INTERESTED_PARENT
}

