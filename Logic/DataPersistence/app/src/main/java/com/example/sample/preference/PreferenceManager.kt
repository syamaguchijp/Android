package com.example.sample.preference

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.content.Context

class PreferenceManager {

    companion object {
        val PREFERENCES_NAME = "myPref"
        var prefs: SharedPreferences? = null

        fun put(key: String, value: String, context: Context) {
            if (prefs == null) {
                prefs = getPref(context)
            }
            prefs?.let {
                val e: SharedPreferences.Editor = it.edit()
                e.putString(key, value)
                e.apply()
            }
        }

        fun get(key: String, context: Context): String? {
            if (prefs == null) {
                prefs = getPref(context)
            }
            val p = prefs ?: return null
            return p.getString(key, null)
        }

        private fun getPref(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREFERENCES_NAME, AppCompatActivity.MODE_PRIVATE)
        }
    }
}