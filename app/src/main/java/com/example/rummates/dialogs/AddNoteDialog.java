package com.example.rummates.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.rummates.R;
import com.example.rummates.controllers.EndpointController;
import com.example.rummates.entities.notesEntity.Note;
import com.example.rummates.entities.notesEntity.NotesEntity;
import com.example.rummates.entities.shoppinglistEntity.Item;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.example.rummates.fragments.NoteFragment;

import java.util.Calendar;
import java.util.Objects;

public class AddNoteDialog extends DialogFragment {
    private EditText note;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_notes_addnote,null);
        note = view.findViewById(R.id.add_note);

        builder.setView(view)
                .setTitle("Add Note")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}})
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input = note.getText().toString();
                        if(!input.equals("")){
                            NotesEntity notesEntity = EndpointController.getInstance().getNotesForGroup();
                            notesEntity.getNotes().add(new Note(input, "anncisz", Calendar.getInstance().getTime().toString()));

                            EndpointController.getInstance().getNotesEndpoint().updateDatabase(notesEntity);
                        }
                    }});

        return builder.create();
    }

    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

}
