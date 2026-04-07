package com.udacity.pawhaven.data

internal object Repository {

    val validVolunteers = setOf(
        newVolunteer("Grace", "Johnson", 28),
        newVolunteer("Samuel", "Okoye", 35),
        newVolunteer("Amina", "Diallo", 30),
        newVolunteer("Marie", "Dupont", 26)
    )

    val pets = mutableListOf<Animal>(
        Dog("Bella", 2),
        Cat("Max", 3),
        Elephant( "Charlie", 5),
        Bird("Cindy", 4),
        Lion("Darrel", 4),
        Parrot( "Parry", 1),
        )

    var user : Person? = null

    fun getPetById(id: String): Animal? {
        return pets.find { it.id == id }
    }
}