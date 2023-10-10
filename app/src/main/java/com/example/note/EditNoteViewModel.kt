package com.example.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditNoteViewModel(noteId: Long,val dao: NoteDao) : ViewModel() {
    val note = dao.get(noteId)
    var newNoteName = ""
    var newNoteDescription = ""
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList
    fun addNote() {
        viewModelScope.launch {
            val note = Note()
            note.noteName = newNoteName
            note.noteDescription =newNoteDescription
            dao.insert(note)
            _navigateToList.value = true
        }
    }
    fun updateNote() {
        viewModelScope.launch {
            dao.update(note.value!!)
            _navigateToList.value = true
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}