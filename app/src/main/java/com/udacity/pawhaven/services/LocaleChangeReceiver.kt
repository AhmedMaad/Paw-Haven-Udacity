package com.udacity.pawhaven.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class LocaleChangeReceiver(
    private val listener: LocaleChangeListener
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_LOCALE_CHANGED) {
            listener.onLocaleChanged()
        }
    }
}


interface LocaleChangeListener {
    fun onLocaleChanged()
}

