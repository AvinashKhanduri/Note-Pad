package com.example.notepad.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)// It should be an abstract class.
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDataBaseDAo

}



