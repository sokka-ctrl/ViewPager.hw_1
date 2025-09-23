package com.example.pager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pager.models.NotesModel

@Dao
interface NotesDao {
    @Query("SELECT  * FROM notes_list ORDER BY notesData DESC")
    fun getAllNotes(): List<NotesModel>

    @Insert
    fun addNote(notesModel: NotesModel)
    @Query("DELETE FROM notes_list WHERE id = :noteId")
    fun deleteNote(noteId: Int)
    @Update
    fun updateNote(note: NotesModel)
}