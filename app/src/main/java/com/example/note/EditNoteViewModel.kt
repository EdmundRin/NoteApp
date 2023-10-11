package com.example.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
/**
 * ViewModel for editing and managing a single note.
 *
 * This ViewModel is responsible for handling the editing and management of a single note's details.
 * It provides functions to update and delete the note, as well as methods to observe and navigate
 * to a list of notes.
 *
 * @param noteId The unique identifier of the note to be edited.
 * @param dao The Data Access Object (DAO) for interacting with the note database.
 */
class EditNoteViewModel(noteId: Long,val dao: NoteDao) : ViewModel() {
    val note = dao.get(noteId)
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList
    /**
     * Initiates an update operation to modify the details of the note.
     */
    fun updateNote() {
        viewModelScope.launch {
            dao.update(note.value!!)
            _navigateToList.value = true
        }
    }
    /**
     * Initiates a deletion operation to remove the note.
     */
    fun deleteTask() {
        viewModelScope.launch {
            dao.delete(note.value!!)
            _navigateToList.value = true
        }
    }
    /**
     * Resets the navigation flag to prevent further navigation until explicitly triggered.
     */
    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}