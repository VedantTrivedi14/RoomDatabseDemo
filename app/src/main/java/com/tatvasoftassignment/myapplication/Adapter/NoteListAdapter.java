package com.tatvasoftassignment.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tatvasoftassignment.myapplication.Activity.EditNoteActivity;
import com.tatvasoftassignment.myapplication.Activity.MainActivity;
import com.tatvasoftassignment.myapplication.Model.Note;
import com.tatvasoftassignment.myapplication.R;

import java.util.List;


public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    public interface OnDeleteClickListener{
        void OnDeleteClickListener(Note myNote);
    }

   public List<Note> mNotes;
    Context context;
    OnDeleteClickListener onDeleteClickListener;

    public NoteListAdapter(Context context,OnDeleteClickListener listener) {

        this.context = context;
        this.onDeleteClickListener = listener;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
        if (mNotes != null) {
            Note note = mNotes.get(i);
            noteViewHolder.setData(note.getNote(), i);
            noteViewHolder.setListeners();
        } else {
            noteViewHolder.txtNote.setText(R.string.no_note);
        }

    }

    @Override
    public int getItemCount() {
        if(mNotes != null)
            return mNotes.size();
        return 0;
    }

    public  class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView txtNote;
        int mPosition;
        ImageButton imgEdit,imgDelete;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNote = (itemView).findViewById(R.id.txtNote);
            imgEdit = (itemView).findViewById(R.id.imgEdit);
            imgDelete = (itemView).findViewById(R.id.imgDelete);
        }

        public void setData(String note, int i) {
            txtNote.setText(note);
            mPosition = i;

        }

        public void setListeners() {

            imgEdit.setOnClickListener(itemView->{
                Intent intent = new Intent(context, EditNoteActivity.class);
                intent.putExtra("note_id",mNotes.get(mPosition).getId());
                ((Activity)context).startActivityForResult(intent, MainActivity.UPDATE_REQ_CODE);
            });
            imgDelete.setOnClickListener(itemView->{
                if(onDeleteClickListener != null){
                    onDeleteClickListener.OnDeleteClickListener(mNotes.get(mPosition));
                }
            });

        }

    }
}
