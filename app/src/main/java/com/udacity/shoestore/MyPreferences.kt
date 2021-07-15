package com.udacity.shoestore

import android.content.Context
import android.content.SharedPreferences

const val MY_PREFERENCES_FILE = "MySharedPreference"
const val LOGIN_STATE_KEY = "LoginState"

class MyPreferences(context: Context) {
    val preference: SharedPreferences = context.getSharedPreferences(MY_PREFERENCES_FILE, Context.MODE_PRIVATE)

    fun getLoginState(): Boolean {
        return preference.getBoolean(LOGIN_STATE_KEY, false)
    }

    fun setLoginState(newState: Boolean) {
        val editor = preference.edit()
        editor.putBoolean(LOGIN_STATE_KEY, newState)
        editor.apply()
    }
}