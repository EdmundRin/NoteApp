package com.example.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory class for creating instances of the [NoteViewModel].
 *
 * This factory is used to create instances of the [NoteViewModel] and provides the necessary
 * dependencies, such as the data access object (DAO) for accessing notes.
 *
 * @param dao The data access object (DAO) for notes, which will be provided to the created
 * [NoteViewModel] instances.
 */
class NoteViewModelFactory(private val dao: NoteDao)
    : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the specified ViewModel class with the provided DAO.
     *
     * @param modelClass The class of the ViewModel to create.
     * @return A new instance of the ViewModel class with the provided DAO.
     * @throws IllegalArgumentException If the specified ViewModel class is unknown or not supported.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}