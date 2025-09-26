package com.example.pager

import android.app.Application
import androidx.room.Room
import com.example.pager.data.local.room.DataBase

class App : Application() {
    companion object {
        lateinit var db: DataBase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, DataBase::class.java, "notes_data")
            .allowMainThreadQueries().build()

    }
}