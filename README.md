```md
# 🐾 PawHaven — Solution Guide

Welcome to the **PawHaven Solution Project**.  
This repository contains a **complete, working implementation** of the PawHaven Android app, built according to the project requirements and rubric.

This solution is provided to help reviewers, instructors, and maintainers understand the intended architecture, behavior, and learning outcomes of the project.

---

## 📱 Project Overview

**PawHaven** is an Android app for a unique animal shelter where *all kinds of animals*—from dogs and cats to zebras and lions—can find a home.

The app demonstrates core Android and Kotlin concepts taught in Course 1, including:

- Kotlin fundamentals and object-oriented programming
- Android UI with XML and Material Components
- Activities, Fragments, and navigation
- Lifecycle handling and state preservation
- Custom Views and basic animation
- Sound playback using a provided component
- Deep links and Android app components

---

## 🧠 Learning Goals Demonstrated

This solution demonstrates how a student can:

- Model real-world data using Kotlin classes and inheritance
- Build multi-screen Android apps using Activities and Fragments
- Pass data safely between screens
- Handle configuration changes such as rotation
- Use reusable UI components and custom views
- Integrate Android framework components beyond Activities
- Follow clean project structure and Android best practices

---

## 🗂 Project Structure

```

app/
├── data/
│   ├── Animal.kt
│   ├── Dog.kt
│   ├── Cat.kt
│   ├── Lion.kt
│   ├── Zebra.kt
│   ├── Person.kt
│   └── Repository.kt
│
├── AbstractBaseActivity // Provided, to help support multi-language change
├── MainActivity // Welcome activity
├── AddAnimalActivity
├── PetDetailActivity
├── PetListActivity
└── ProfileActivity // registration page
│   
│
├── components/
│   ├── PlayPauseView.kt        // Provided component
│   └── PetRowComponent.xml
│
├── fragments/
│   ├── PetDetailFragment.kt    
│   └── PetListFragment.xml
│
├── services/
│   └── LocaleChangeReceiver.kt // Provided
│
├── MainActivity.kt
│
└── AndroidManifest.xml

```

---

## 🐶 Animal Modeling (Kotlin & OOP)

- `Animal` is an **abstract parent class** with:
  - `name`, `age`, `description`, `imageRes`, `soundRes`
  - A generated `id` using `UUID`
- Subclasses such as `Dog`, `Cat`, `Lion`, and `Zebra` inherit from `Animal`
- Each subclass provides default image and sound resources using the `AnimalType` enum
- `Animal` implements a `Talkable` interface for sound playback
- A `Person` data class models user profile information and roles

This structure demonstrates inheritance, polymorphism, and encapsulation.

---

## 🏠 App Screens & Navigation

### Welcome Screen
- Launcher activity
- Introduces the app
- Routes to the Registration/Profile screen

### Registration Page
- Collects user details (name, age, role)
- Validates required input
- Validates volunteers against a predefined list
- Navigates to the Pet List screen

### Pet List Screen
- Displays animals using `PetListFragment`
- Supports:
  - Single-pane layout on phones
  - Two-pane (list + detail) layout on tablets using `sw600dp`
- Handles:
  - Pet selection
  - Add Animal navigation
  - Sound playback per row

### Pet Detail Screen
- Implemented as a `Fragment`
- Displays:
  - Animal image
  - Description
  - Play button
  - Animated `SoundPulseView`
  - “Adopt A Paw” action

---

## ➕ Add Animal Flow

- `AddAnimalActivity` allows users to add a new animal
- Users select:
  - Name
  - Age
  - Animal type
- Preview updates automatically based on selection
- On completion:
  - Animal is returned via Intent
  - Added to the repository
  - Pet list refreshes automatically

---

## 🔊 Sound Playback

- Sound playback is handled through the provided `PlayPauseView`
- The solution:
  - Connects `PlayPauseView` to the selected `Animal`
  - Ensures only one sound plays at a time
  - Properly releases resources during lifecycle events
---

## 📦 Android Components Used

- **Activities** for navigation
- **Fragments** for flexible UI
---

## ✅ How This Solution Meets the Rubric

This solution meets **all required criteria** across:

- Kotlin & OOP design
- UI, navigation, and lifecycle handling
- Custom views and Android components

It also includes optional enhancements such as:
- Sharing
- Multi-language support

However, optional enhancements such as Deep Links, Profile edits are not included in the solution.
---

## 📌 Final Notes

This solution represents **one possible correct implementation**.  
Student submissions may differ in structure or style while still meeting project requirements.

For grading, always refer back to the **project rubric**.
```
