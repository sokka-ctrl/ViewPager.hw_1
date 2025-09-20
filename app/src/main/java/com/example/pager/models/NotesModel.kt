package com.example.pager.models

import androidx.room.DeleteColumn
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_list")
data class NotesModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
val notesTitle: String,
val notesDesc: String,
val notesData: String,
    val notesColor: String
)