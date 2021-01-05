package com.example.almatyparking.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private val APP_SETTINGS = "APP_SETTINGS"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
    }

    fun getUserId(context: Context): Int? =
        getSharedPreferences(context).getInt("user_id", 0)

    fun setUserId(context: Context, newValue: Int?) {
        val editor = getSharedPreferences(context).edit()
        if (newValue != null) {
            editor.putInt("user_id", newValue)
        }
        editor.commit()
    }


    fun getBalance(context: Context): Int? =
        getSharedPreferences(context).getInt("balance", 0)

    fun setBalance(context: Context, newValue: Int?) {
        val editor = getSharedPreferences(context).edit()
        if (newValue != null) {
            editor.putInt("balance", newValue)
        }
        editor.commit()
    }
}