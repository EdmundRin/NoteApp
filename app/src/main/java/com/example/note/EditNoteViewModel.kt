package com.example.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditNoteViewModel(val dao: NoteDao) : ViewModel() {
    var newNoteName = ""
    var newNoteDescription = ""
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList
    fun saveNote() {
        viewModelScope.launch {
            val note = Note()
            note.noteName = newNoteName
            note.noteDescription =newNoteDescription
            dao.insert(note)
            _navigateToList.value = true
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}