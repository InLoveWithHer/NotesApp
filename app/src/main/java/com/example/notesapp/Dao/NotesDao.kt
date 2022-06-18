package com.example.notesapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.Model.Notes

@Dao
abstract class NotesDao {

    @Query("SELECT * FROM Notes")
    abstract fun getNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=3")
    abstract fun getHighNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    abstract fun getMediumNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    abstract fun getLowNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    abstract fun deleteNotes(id: Int)

    @Update
    abstract fun updateNotes(notes: Notes)

}
