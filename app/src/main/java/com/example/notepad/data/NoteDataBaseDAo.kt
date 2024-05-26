package com.example.notepad.data

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao // It should be a interface or a abstract class.
interface NoteDataBaseDAo{
    @Query("SELECT * FROM NOTES_TBL")
    fun getNotes():Flow<List<Note>>

    @Query("select * from notes_tbl where id=:id")
    suspend fun getNoteById(id:String):Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note:Note)

    @Query("Delete  from notes_tbl")
    suspend fun deleteAll()

    @Delete(entity = Note::class)
    suspend fun deleteNote(note:Note)

}