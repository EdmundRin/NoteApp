package com.example.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
/**
 * Factory class for creating an EditNoteViewModel.
 *
 * This factory is responsible for instantiating the EditNoteViewModel by providing it with
 * the necessary dependencies, such as the `noteId` and the Data Access Object (`dao`).
 *
 * @param noteId The unique identifier of the note to be edited.
 * @param dao The Data Access Object for accessing and managing note data in the database.
 */
class EditNoteViewModelFactory(private val noteId: Long,
                               private val dao: NoteDao)
    : ViewModelProvider.Factory {
    /**
     * Creates an instance of the specified ViewModel class.
     *
     * @param modelClass The class of the ViewModel to be created.
     * @return The created ViewModel instance.
     * @throws IllegalArgumentException if an unknown ViewModel class is provided.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditNoteViewModel::class.java)) {
            return EditNoteViewModel(noteId, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}