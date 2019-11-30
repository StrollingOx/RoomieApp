package com.example.rummates.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.R;
import com.example.rummates.adapters.NotesAdapter;
import com.example.rummates.adapters.ShoppingListAdapter;
import com.example.rummates.controllers.EndpointController;
import com.example.rummates.dialogs.AddNoteDialog;
import com.example.rummates.dialogs.AddProductDialog;
import com.example.rummates.entities.notesEntity.Note;
import com.example.rummates.entities.notesEntity.NotesEntity;
import com.example.rummates.entities.shoppinglistEntity.Item;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NoteFragment extends Fragment {
    private final String TAG = "NotesFragment";

    private FloatingActionButton addButton;

    private ArrayList<Note> notes;
    private RecyclerView NotesRV;
    private RecyclerView.LayoutManager layoutManager;

    private NotesAdapter notesAdapter;
    private NotesEntity noteEntity;

    private String groupID = "5dc6ba9c2585a92b30b3fb81";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        getNotesForCurrentGroup(groupID);
        initAddButton(view);
        initRecyclerView(view);

        AddNoteDialog d = new AddNoteDialog(groupID);
        d.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                getNotesForCurrentGroup(groupID);
            }
        });

        return view;
    }

    private void initAddButton(View view) {
        addButton = (FloatingActionButton) view.findViewById(R.id.add_note_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AddNoteDialog addNoteDialog = new AddNoteDialog(groupID);
                addNoteDialog.show((getActivity()).getSupportFragmentManager(), "dialog");
            }
        });

    }

    //TODO: Refresh adapter periodically

    private void getNotesForCurrentGroup(String groupID) {
        noteEntity = EndpointController.getInstance(getContext()).getNotesForGroup(groupID);
        if(notes != noteEntity.getNotes())
            notes = noteEntity.getNotes();
        Log.d(TAG, "Notes Updated");
    }

    private void initRecyclerView(View view){
        NotesRV = view.findViewById(R.id.notes_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        notesAdapter = new NotesAdapter(notes, getContext(), groupID);
        NotesRV.setLayoutManager(layoutManager);
        NotesRV.setAdapter(notesAdapter);

        Log.d(TAG, "RecyclerView initiated");
    }
}
