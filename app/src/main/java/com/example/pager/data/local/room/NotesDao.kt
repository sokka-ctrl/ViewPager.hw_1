package com.example.pager.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pager.data.models.NotesModel

@Dao
interface NotesDao {
    @Query("SELECT  * FROM notes_list ORDER BY notesData DESC")
    fun getAllNotes(): List<NotesModel>

    @Insert
    fun addNote(notesModel: NotesModel)
    @Delete
    fun deleteNote(note: NotesModel)

    @Update
    fun updateNote(note: NotesModel)
}