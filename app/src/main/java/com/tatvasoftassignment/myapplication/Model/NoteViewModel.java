package com.tatvasoftassignment.myapplication.Model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tatvasoftassignment.myapplication.database.NoteDao;
import com.tatvasoftassignment.myapplication.database.NoteRoomDB;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    NoteRoomDB noteDB;
    NoteDao noteDao;
    LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteDB = NoteRoomDB.getDatabase(application);
        noteDao = noteDB.noteDao();
        mAllNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {new UpdateAsyncTask(noteDao).execute(note);}

    public void delete(Note note) {new DeleteAsyncTask(noteDao).execute(note);}

    public LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("main", "ViewModel Destroyed");
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao mNoteDao;

        public InsertAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);

            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Note, Void, Void>{
        NoteDao mNoteDao;
        public UpdateAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }


    private class DeleteAsyncTask extends AsyncTask<Note, Void, Void>{
        NoteDao mNoteDao;
        public DeleteAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.delete(notes[0]);
            return null;
        }
    }
}
