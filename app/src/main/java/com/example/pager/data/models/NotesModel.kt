package com.example.pager.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "notes_list")
data class NotesModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
val notesTitle: String,
val notesDesc: String,
val notesData: String,
    val notesColor: String
) : Serializable