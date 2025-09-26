package com.example.pager.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pager.data.models.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class DataBase: RoomDatabase() {

    abstract  fun dao(): NotesDao

}