package com.example.notepad.model

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.data.Note
import com.example.notepad.data.NoteDataSource
import com.example.notepad.data.NoteDatabase
import com.example.notepad.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository):ViewModel (){
   //private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()//The asStateFlow() is an extension function in Kotlin that is commonly used with the MutableStateFlow class.
    // It's used to convert a mutable state flow into an immutable state flow .The resulting noteList state flow is immutable, meaning that external components can observe
    // changes but cannot modify the state directly. This aligns with the principles of immutability, reducing the risk of unintended modifications to the state.

    init {
       viewModelScope.launch(Dispatchers.IO) {
           repository.getAllNotes().distinctUntilChanged().collect{listNotes->
              // if (listNotes.isNullOrEmpty()){
                   Log.d("empty", "empty list: ")
              // }
                   _noteList.value = listNotes

           }
       }
    }

     fun addNote(note: Note){
        viewModelScope.launch { repository.addNote(note = note) }
    }

     fun updateNote(note: Note){
        viewModelScope.launch { repository.addNote(note = note) }
    }

     fun removeNote(note:Note){
        viewModelScope.launch{ repository.deleteNote(note = note) }
    }





}