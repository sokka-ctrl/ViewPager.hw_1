package com.example.pager.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotesModel(
val notesTitle: String,
val notesDesc: String,
val notesData: String,
val notesColor: Int
): Parcelable