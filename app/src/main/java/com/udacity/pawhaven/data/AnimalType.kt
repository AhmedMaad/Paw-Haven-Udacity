package com.udacity.pawhaven.data

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.udacity.pawhaven.R


enum class AnimalType(
    val label: String,
    @DrawableRes val defaultIconRes: Int,
    @RawRes val defaultSoundRes: Int
) {
    DOG("Dog", R.drawable.ic_dog, R.raw.dog_bark),
    CAT("Cat", R.drawable.ic_cat, R.raw.cat_meow),
    PARROT("Parrot", R.drawable.ic_parrot, R.raw.parrot_chirp),
    ELEPHANT("Elephant", R.drawable.ic_elephant, R.raw.elephant_trumpet),
    LION("Lion", R.drawable.ic_lion, R.raw.lion_roar),
    BIRD("Bird", R.drawable.ic_bird, R.raw.bird_chirp);

    fun defaultDescription(name: String): String =
        "$name is a cheerful ${label.lowercase()}."

    fun createAnimal(
        name: String,
        age: Int,
        description: String = defaultDescription(name),
        @DrawableRes imageRes: Int = defaultIconRes,
        @RawRes soundRes: Int = defaultSoundRes
    ): Animal =
        when (this) {
            DOG -> Dog(name, age, description, imageRes, soundRes)
            CAT -> Cat(name, age, description, imageRes, soundRes)
            PARROT -> Parrot(name, age, description, imageRes, soundRes)
            ELEPHANT -> Elephant(name, age, description, imageRes, soundRes)
            LION -> Lion(name, age, description, imageRes, soundRes)
            BIRD -> Bird(name, age, description, imageRes, soundRes)
        }
}