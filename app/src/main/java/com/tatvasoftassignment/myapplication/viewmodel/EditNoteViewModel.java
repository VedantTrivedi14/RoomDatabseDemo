package com.tatvasoftassignment.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tatvasoftassignment.myapplication.database.NoteDao;
import com.tatvasoftassignment.myapplication.database.NoteRoomDB;
import com.tatvasoftassignment.myapplication.model.Note;

public class EditNoteViewModel extends AndroidViewModel {

    NoteRoomDB noteDB;
    NoteDao noteDao;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        Log.i("Edit","Edit View Model");
        noteDB = NoteRoomDB.getDatabase(application);
        noteDao = noteDB.noteDao();


    }
    public LiveData<Note> getNote(String noteId){
        return noteDao.getNote(noteId);
    }
}
