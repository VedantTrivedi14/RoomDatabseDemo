package com.tatvasoftassignment.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tatvasoftassignment.myapplication.R;
import com.tatvasoftassignment.myapplication.Utils.Constant;
import com.tatvasoftassignment.myapplication.databinding.ActivityNewNoteBinding;

public class NewNoteActivity extends AppCompatActivity {

    private ActivityNewNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        binding = ActivityNewNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnClick.setOnClickListener(v -> {
            if (isValid()) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(Constant.note,binding.etNote.getText().toString());
                    setResult(RESULT_OK, resultIntent);
            }
            finish();
        });


    }

    Boolean isValid() {
        boolean valid = true;
        if (binding.etNote.getText().toString().length() == 0) {
            Toast.makeText(this, R.string.note, Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }
}