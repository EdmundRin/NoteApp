package com.example.note

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
/**
 * The Room Database for storing notes.
 *
 * This class represents the Room Database used to store notes in the app. It defines a set of
 * entities and their corresponding Data Access Objects (DAOs). The database is versioned and
 * supports schema migration.
 *
 * @property noteDao The Data Access Object for managing Note entities.
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /**
         * Get an instance of the NoteDatabase.
         *
         * This function provides a singleton instance of the NoteDatabase, creating it if it
         * doesn't already exist. It ensures that only one instance of the database is created.
         *
         * @param context The application context.
         * @return An instance of the NoteDatabase.
         */
        fun getInstance(context: Context): NoteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}