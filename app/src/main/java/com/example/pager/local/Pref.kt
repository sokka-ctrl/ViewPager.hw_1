package com.example.pref.local
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.pager.AppKey

class Pref(context: Context) {

    val pref: SharedPreferences = context.getSharedPreferences(AppKey.KeyR, Context.MODE_PRIVATE)

    fun saveTepter(tepter: Boolean) {
        pref.edit {
            putBoolean(AppKey.KeyM, tepter)
        }
    }

    fun getTepter(): Boolean {
        return pref.getBoolean(AppKey.KeyM, true)
    }
}
