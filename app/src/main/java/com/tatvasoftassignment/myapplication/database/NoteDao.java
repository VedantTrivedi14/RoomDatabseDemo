package com.tatvasoftassignment.myapplication.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.tatvasoftassignment.myapplication.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

   @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

   @Query("SELECT * FROM notes  WHERE id=:noteId")
    LiveData<Note> getNote(String noteId);

   @Update
    void update(Note note);

   @Delete
    void delete(Note note);
}
