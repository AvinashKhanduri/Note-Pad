package com.example.notepad.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID
import javax.annotation.Nonnull


@Entity(tableName = "notes_tbl")// It should be an data class
data class Note(

    @PrimaryKey
    val id:UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    val title:String,

    @ColumnInfo(name = "note_description")
    val description:String,

//    @ColumnInfo(name = "entry_date")
//    val entryDate:Date = Date.from(Instant.now()),
)
