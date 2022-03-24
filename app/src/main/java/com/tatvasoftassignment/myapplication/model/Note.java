package com.tatvasoftassignment.myapplication.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "notes")
public class Note {
    @NonNull
    @PrimaryKey
    private final String id;

    @NonNull
    @ColumnInfo(name = "note")
    private final String mNote;

    public Note(@NonNull String id, @NonNull String note) {
        this.id = id;
        this.mNote = note;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getNote() {
        return mNote;
    }


}
