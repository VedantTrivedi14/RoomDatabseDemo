package com.tatvasoftassignment.myapplication.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tatvasoftassignment.myapplication.utils.Constant;
import com.tatvasoftassignment.myapplication.viewmodel.EditNoteViewModel;
import com.tatvasoftassignment.myapplication.model.Note;
import com.tatvasoftassignment.myapplication.R;
import com.tatvasoftassignment.myapplication.databinding.ActivityEditNoteBinding;

public class EditNoteActivity extends AppCompatActivity {



    EditNoteViewModel noteViewModel;
    private String noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        ActivityEditNoteBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note);
        setContentView(binding.getRoot());
        noteViewModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            noteId = bundle.getString(Constant.note_id);
        }

        LiveData<Note> note = noteViewModel.getNote(noteId);
        note.observe(this, note1 -> {
            assert note1 != null;
            binding.etNote.setText(note1.getNote());
        });
        binding.btnEdit.setOnClickListener(v->{
            Intent resultIntent = new Intent();
            resultIntent.putExtra(Constant.note_id,noteId);
            resultIntent.putExtra(Constant.update_note,binding.etNote.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();

        });
        binding.btnCancel.setOnClickListener(v-> finish());

    }
}