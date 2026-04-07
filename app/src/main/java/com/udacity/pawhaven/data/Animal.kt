package com.udacity.pawhaven.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import kotlinx.parcelize.Parcelize
import java.util.UUID


abstract class Animal(
    open val name: String,
    open val age: Int,
    open val description: String,
    open val imageRes: Int,
    open val soundRes: Int
) : Parcelable {
    val id: String = UUID.randomUUID().toString()
}


@Parcelize
class Dog(
    override val name: String,
    override val age: Int,
    override val description: String = AnimalType.DOG.defaultDescription(name),
    @DrawableRes override val imageRes: Int = AnimalType.DOG.defaultIconRes,
    @RawRes override val soundRes: Int = AnimalType.DOG.defaultSoundRes
) : Animal(name, age, description, imageRes, soundRes), Parcelable

@Parcelize
class Cat(
    override val name: String,
    override val age: Int,
    override val description: String = AnimalType.CAT.defaultDescription(name),
    @DrawableRes override val imageRes: Int = AnimalType.CAT.defaultIconRes,
    @RawRes override val soundRes: Int = AnimalType.CAT.defaultSoundRes
) : Animal(name, age, description, imageRes, soundRes), Parcelable

@Parcelize
class Elephant(
    override val name: String,
    override val age: Int,
    override val description: String = AnimalType.ELEPHANT.defaultDescription(name),
    @DrawableRes override val imageRes: Int = AnimalType.ELEPHANT.defaultIconRes,
    @RawRes override val soundRes: Int = AnimalType.ELEPHANT.defaultSoundRes
) : Animal(name, age, description, imageRes, soundRes), Parcelable

@Parcelize
class Bird(
    override val name: String,
    override val age: Int,
    override val description: String = AnimalType.BIRD.defaultDescription(name),
    @DrawableRes override val imageRes: Int = AnimalType.BIRD.defaultIconRes,
    @RawRes override val soundRes: Int = AnimalType.BIRD.defaultSoundRes
) : Animal(name, age, description, imageRes, soundRes), Parcelable

@Parcelize
class Lion(
    override val name: String,
    override val age: Int,
    override val description: String = AnimalType.LION.defaultDescription(name),
    @DrawableRes override val imageRes: Int = AnimalType.LION.defaultIconRes,
    @RawRes override val soundRes: Int = AnimalType.LION.defaultSoundRes
) : Animal(name, age, description, imageRes, soundRes), Parcelable

@Parcelize
class Parrot(
    override val name: String,
    override val age: Int,
    override val description: String = AnimalType.PARROT.defaultDescription(name),
    @DrawableRes override val imageRes: Int = AnimalType.PARROT.defaultIconRes,
    @RawRes override val soundRes: Int = AnimalType.PARROT.defaultSoundRes
) : Animal(name, age, description, imageRes, soundRes), Parcelable