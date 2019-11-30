package com.example.rummates.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.R;
import com.example.rummates.controllers.EndpointController;
import com.example.rummates.entities.notesEntity.Note;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private ArrayList<Note> arrayNotes;
    private OnItemClickListener notesItemListener;
    private String groupID;
    private Context mContext;

    public NotesAdapter(ArrayList<Note> notes, Context context, String groupID) {
        this.arrayNotes = notes;
        this.mContext = context;
        this.groupID = groupID;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        //void onDeleteClick(int position);
        //void onSomethingOtherClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        notesItemListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notes_note,
                parent, false);
        return new ViewHolder(view, notesItemListener);
    }

    @Override
    public int getItemCount() {
        return arrayNotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteContent, noteMenu;


        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            noteContent = itemView.findViewById(R.id.note);
            noteMenu  = itemView.findViewById(R.id.note_menu);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) //position must be valid
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position){ //final int position{
        final Note currentNote = arrayNotes.get(position);

        if(currentNote != null) {
            holder.noteContent.setText(currentNote.getContent());
        }

        holder.noteMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v); //hmmmmmmmmmmm
                popup.inflate(R.menu.layout_notes_note_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.note_delete:
                                Note note = new Note(arrayNotes.get(position).getContent());
                                System.out.println(note.getContent());
                                arrayNotes.remove(position);
                                EndpointController.getInstance().deleteNoteForGroup(groupID, note);

                                notifyItemRemoved(position);
                                return true;
                            default:
                                return false;
                        }
                    }}); popup.show();
            }
        });

    }
}
