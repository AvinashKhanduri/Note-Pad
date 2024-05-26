package com.example.notepad.repository

import com.example.notepad.data.Note
import com.example.notepad.data.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabase: NoteDatabase) {
    suspend fun addNote(note:Note){
        noteDatabase.noteDao().insert(note = note)
    }

    suspend fun updateNote(note: Note){
        noteDatabase.noteDao().update(note = note)
    }

    suspend fun deleteNote(note: Note){
        noteDatabase.noteDao().deleteNote(note = note)
    }

    suspend fun deleteAll(){
        noteDatabase.noteDao().deleteAll()
    }

    fun getAllNotes():Flow<List<Note>> {
        return noteDatabase.noteDao().getNotes().flowOn(Dispatchers.IO).conflate()
    }



}