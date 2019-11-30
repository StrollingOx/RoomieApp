package com.example.rummates.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.Errors.ErrorTags;
import com.example.rummates.R;
import com.example.rummates.adapters.NotesAdapter;
import com.example.rummates.controllers.EndpointController;
import com.example.rummates.dialogs.AddNoteDialog;
import com.example.rummates.entities.UserEntity;
import com.example.rummates.entities.notesEntity.Note;
import com.example.rummates.entities.notesEntity.NotesEntity;
import com.example.rummates.serializer.UserSerializer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NoteFragment extends Fragment {
    private final String TAG = "NotesFragment";
    private String groupID = ErrorTags.ERROR_NO_GROUP_ID;
    private UserEntity user;

    private FloatingActionButton addButton;

    private ArrayList<Note> notes;
    private RecyclerView NotesRV;
    private RecyclerView.LayoutManager layoutManager;

    private NotesAdapter notesAdapter;
    private NotesEntity noteEntity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        getExtras();
        getFirstGroupId();
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

    private void getExtras() {
        Bundle b = getActivity().getIntent().getExtras();
        if(b != null){
            user = UserSerializer.userDeserializer(getActivity().getIntent().getExtras().getString("user"));
        }
    }

    private void getFirstGroupId() {
        if(user != null)
            if(user.getGroups().size() != 0)
                this.groupID = user.getGroups().get(0).getId();
            else
                groupID = "5dc6ba9c2585a92b30b3fb81";
        //TODO: if no groups -> go to CreateGroup
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
