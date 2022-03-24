package com.tatvasoftassignment.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tatvasoftassignment.myapplication.R;
import com.tatvasoftassignment.myapplication.activity.EditNoteActivity;
import com.tatvasoftassignment.myapplication.activity.MainActivity;
import com.tatvasoftassignment.myapplication.databinding.ListItemBinding;
import com.tatvasoftassignment.myapplication.model.Note;
import com.tatvasoftassignment.myapplication.utils.Constant;

import java.util.List;


public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {


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

        ListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item,parent,false);
        return new NoteViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
        if (mNotes != null) {
            Note note = mNotes.get(i);
            noteViewHolder.setData(note.getNote(), i);
            noteViewHolder.setListeners();
        } else {
            noteViewHolder.binding.txtNote.setText(R.string.no_note);
        }

    }

    @Override
    public int getItemCount() {
        if(mNotes != null)
            return mNotes.size();
        return 0;
    }

    public  class NoteViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;
        int mPosition;

        public NoteViewHolder( ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(String note, int i) {
            binding.txtNote.setText(note);
            mPosition = i;
        }

        public void setListeners() {

            binding.imgEdit.setOnClickListener(itemView->{
                Intent intent = new Intent(context, EditNoteActivity.class);
                intent.putExtra(Constant.note_id,mNotes.get(mPosition).getId());
                ((Activity)context).startActivityForResult(intent, MainActivity.UPDATE_REQ_CODE);
            });
            binding.imgDelete.setOnClickListener(itemView->{
                if(onDeleteClickListener != null){
                    onDeleteClickListener.onDeleteClickListener(mNotes.get(mPosition));
                }
            });

        }

    }
    public interface OnDeleteClickListener{
        void onDeleteClickListener(Note myNote);
    }
}
