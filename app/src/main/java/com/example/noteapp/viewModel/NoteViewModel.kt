package com.example.noteapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.Database.NoteDatabase
import com.example.noteapp.Database.NoteRepository
import com.example.noteapp.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application:Application):AndroidViewModel(application) {
    private val noteRepository:NoteRepository
    val allnote:LiveData<List<Note>>
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        noteRepository = NoteRepository(dao)
        allnote = noteRepository.allNotes
    }

    fun deleteNote(note:Note) = viewModelScope.launch ( Dispatchers.IO ){
        noteRepository.delete(note)
    }
    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.insert(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.update(note)
    }
}