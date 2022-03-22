package com.tatvasoftassignment.myapplication.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.tatvasoftassignment.myapplication.Adapter.NoteListAdapter;
import com.tatvasoftassignment.myapplication.Model.Note;
import com.tatvasoftassignment.myapplication.Model.NoteViewModel;
import com.tatvasoftassignment.myapplication.R;
import com.tatvasoftassignment.myapplication.Utils.Constant;
import com.tatvasoftassignment.myapplication.databinding.ActivityMainBinding;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NoteListAdapter.OnDeleteClickListener {

    NoteViewModel noteViewModel;
    public static final int REQ_CODE = 1;
    public static final int UPDATE_REQ_CODE =2;

    NoteListAdapter noteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteListAdapter = new NoteListAdapter( this,this);
        binding.recyclerView.setAdapter(noteListAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewNoteActivity.class);
            startActivityForResult(intent, REQ_CODE);

        });

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, notes -> noteListAdapter.setNotes(notes));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            final String note_id = UUID.randomUUID().toString();
            assert data != null;
            Note note = new Note(note_id, data.getStringExtra(Constant.note));
            noteViewModel.insert(note);

            Toast.makeText(this, R.string.save, Toast.LENGTH_SHORT).show();
        } else if(requestCode == UPDATE_REQ_CODE && resultCode == RESULT_OK){
            assert data != null;
            Note note = new Note( data.getStringExtra(EditNoteActivity.NOTE_ID),
                    data.getStringExtra(EditNoteActivity.UPDATE_NOTE));
            Toast.makeText(this, R.string.updated, Toast.LENGTH_SHORT).show();
            noteViewModel.update(note);
        }else {
            Toast.makeText(this, R.string.not_save, Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void OnDeleteClickListener(Note myNote) {
        noteViewModel.delete(myNote);

    }
}