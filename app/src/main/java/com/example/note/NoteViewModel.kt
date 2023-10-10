package com.example.note


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(val dao: NoteDao) : ViewModel() {
    val note = dao.getAll()
    private val _navigateToNote = MutableLiveData<Long?>()
    val navigateToNote: LiveData<Long?>
        get() = _navigateToNote
    fun onNoteClicked(noteId: Long) {
        _navigateToNote.value = noteId
    }
    fun onNoteNavigated() {
        _navigateToNote.value = null
    }
}