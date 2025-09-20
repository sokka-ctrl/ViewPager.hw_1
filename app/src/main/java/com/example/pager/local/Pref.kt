package com.example.pref.local
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.pager.keys.AppKey

class Pref(context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(AppKey.KEY_R, Context.MODE_PRIVATE)

    fun saveFirstOpen(isOpen: Boolean) {
        pref.edit {
            putBoolean(AppKey.KEY_M, isOpen)
        }
    }

    fun getFirstOpen(): Boolean {
        return pref.getBoolean(AppKey.KEY_M, false)
    }
}
