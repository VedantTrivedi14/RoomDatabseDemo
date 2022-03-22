package com.tatvasoftassignment.myapplication.Activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tatvasoftassignment.myapplication.Model.EditNoteViewModel;
import com.tatvasoftassignment.myapplication.Model.Note;
import com.tatvasoftassignment.myapplication.R;
import com.tatvasoftassignment.myapplication.databinding.ActivityEditNoteBinding;

public class EditNoteActivity extends AppCompatActivity {

    public static final String NOTE_ID = "note_id";
    public static final String UPDATE_NOTE = "note_text";

    EditNoteViewModel noteViewModel;
    private String noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        ActivityEditNoteBinding binding = ActivityEditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            noteId = bundle.getString("note_id");
        }

        LiveData<Note> note = noteViewModel.getNote(noteId);
        note.observe(this, note1 -> {
            assert note1 != null;
            binding.etNote.setText(note1.getNote());
        });
        binding.btnEdit.setOnClickListener(v->{
            Intent resultIntent = new Intent();
            resultIntent.putExtra(NOTE_ID,noteId);
            resultIntent.putExtra(UPDATE_NOTE,binding.etNote.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();

        });
        binding.btnCancel.setOnClickListener(v-> finish());

    }
}