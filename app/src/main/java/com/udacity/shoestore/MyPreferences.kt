package com.udacity.shoestore

import android.content.Context

class MyPreferences(context: Context) {
    val MY_PREFERENCES_FILE = "MySharedPreference"
    val LOGIN_STATE_KEY = "LoginState"

    val preference = context.getSharedPreferences(MY_PREFERENCES_FILE, Context.MODE_PRIVATE)

    fun getLoginState(): Boolean {
        return preference.getBoolean(LOGIN_STATE_KEY, false)
    }

    fun setLoginState(newState: Boolean) {
        val editor = preference.edit()
        editor.putBoolean(LOGIN_STATE_KEY, newState)
        editor.apply()
    }
}