package com.example.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * A data class representing a note.
 *
 * This data class defines the structure of a note entity in the Room database. It includes
 * properties for the unique identifier (noteId), the note's name (noteName), and a description
 * (noteDescription).
 *
 * @param noteId The unique identifier for the note (auto-generated).
 * @param noteName The name or title of the note.
 * @param noteDescription A detailed description or content of the note.
 */
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,
    @ColumnInfo(name = "note_name")
    var noteName: String = "",
    @ColumnInfo(name = "note_description")
    var noteDescription: String = ""
)