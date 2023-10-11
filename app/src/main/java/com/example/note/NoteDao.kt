package com.example.note

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
/**
 * Data Access Object (DAO) for interacting with the note data in the database.
 * This interface provides methods to perform database operations such as insertion, update,
 * deletion, and querying of note records.
 */
@Dao
interface NoteDao {
    /**
     * Insert a new note into the database.
     *
     * @param note The note to be inserted.
     */
    @Insert
    suspend fun insert(note: Note)

    /**
     * Update an existing note in the database.
     *
     * @param note The updated note to be saved.
     */
    @Update
    suspend fun update(note: Note)

    /**
     * Delete a note from the database.
     *
     * @param note The note to be deleted.
     */
    @Delete
    suspend fun delete(note: Note)

    /**
     * Retrieve a specific note by its unique ID.
     *
     * @param key The unique ID of the note to retrieve.
     * @return LiveData containing the requested note or null if not found.
     */
    @Query("SELECT * FROM note_table WHERE noteId = :key")
    fun get(key: Long): LiveData<Note>

    /**
     * Retrieve all notes from the database, ordered by their unique IDs in descending order.
     *
     * @return LiveData containing a list of all notes.
     */
    @Query("SELECT * FROM note_table ORDER BY noteId DESC")
    fun getAll(): LiveData<List<Note>>
}