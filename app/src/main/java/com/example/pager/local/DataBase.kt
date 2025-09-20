package com.example.pager.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pager.NotesDao
import com.example.pager.models.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class DataBase: RoomDatabase() {

    abstract  fun dao(): NotesDao

}