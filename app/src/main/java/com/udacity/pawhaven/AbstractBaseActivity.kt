package com.udacity.pawhaven

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import com.udacity.pawhaven.services.LocaleChangeListener
import com.udacity.pawhaven.services.LocaleChangeReceiver

abstract class BaseActivity : AppCompatActivity(), LocaleChangeListener {

    private lateinit var localeReceiver: LocaleChangeReceiver
    private var lastKnownLanguageTag: String? = null

    override fun onStart() {
        super.onStart()

        localeReceiver = LocaleChangeReceiver(this)
        registerReceiver(localeReceiver, IntentFilter(Intent.ACTION_LOCALE_CHANGED))

        lastKnownLanguageTag = currentLanguageTag()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(localeReceiver)
    }

    override fun onResume() {
        super.onResume()

        // Fallback: catches changes made while the app was in the background
        val now = currentLanguageTag()
        if (lastKnownLanguageTag != null && lastKnownLanguageTag != now) {
            lastKnownLanguageTag = now
            onLocaleChanged()
        }
    }

    private fun currentLanguageTag(): String {
        return resources.configuration.locales[0].toLanguageTag()
    }

    /**
     * Default behavior for all Activities:
     * recreate so strings reload.
     * Students can override in a specific Activity if desired.
     */
    override fun onLocaleChanged() {
        recreate()
    }
}
