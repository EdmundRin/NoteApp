package com.example.note


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel for managing note-related data and navigation within the app.
 *
 * This ViewModel is responsible for handling notes, their details, and navigation within the app.
 * It provides methods to add a new note, trigger navigation to a specific note, and manage
 * the navigation flow.
 *
 * @param dao Data Access Object for note entities.
 */
class NoteViewModel(val dao: NoteDao) : ViewModel() {
    val note = dao.getAll()
    var newNoteName = ""
    var newNoteDescription = ""
    private val _navigateToNote = MutableLiveData<Long?>()
    val navigateToNote: LiveData<Long?>
        get() = _navigateToNote

    /**
     * Adds a new note to the database and initiates navigation to view the newly added note.
     */
    fun addNote() {
        viewModelScope.launch {
            val note = Note()
            note.noteName = newNoteName
            note.noteDescription =newNoteDescription
            dao.insert(note)
            _navigateToNote.value = note.noteId
        }
    }

    /**
     * Initiates navigation to view the note with the specified ID.
     *
     * @param noteId The ID of the note to navigate to.
     */
    fun onNoteClicked(noteId: Long) {
        _navigateToNote.value = noteId
    }

    /**
     * Clears the navigation trigger, allowing the ViewModel to handle future navigation requests.
     */
    fun onNoteNavigated() {
        _navigateToNote.value = null
    }
}