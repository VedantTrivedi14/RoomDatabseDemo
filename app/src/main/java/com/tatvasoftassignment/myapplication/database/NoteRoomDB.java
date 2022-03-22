package com.tatvasoftassignment.myapplication.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tatvasoftassignment.myapplication.Model.Note;


@Database(entities = Note.class, version = 1)
public abstract class NoteRoomDB extends RoomDatabase {

    public abstract NoteDao noteDao();

    public static volatile NoteRoomDB noteRoomInstance;

    public static NoteRoomDB getDatabase(final Context context) {
        if (noteRoomInstance == null) {
            synchronized (NoteRoomDB.class) {
                if (noteRoomInstance == null) {
                    noteRoomInstance = Room.databaseBuilder(context.getApplicationContext(), NoteRoomDB.class, "note_database").build();
                }
            }
        }
        return noteRoomInstance;
    }
}
