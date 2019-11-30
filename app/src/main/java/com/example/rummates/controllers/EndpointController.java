package com.example.rummates.controllers;

import android.content.Context;
import android.util.Log;

import com.example.rummates.endpoints.NotesEndpoint;
import com.example.rummates.endpoints.ShoppingListEndpoint;
import com.example.rummates.entities.notesEntity.Note;
import com.example.rummates.entities.shoppinglistEntity.CommentForItem;
import com.example.rummates.entities.shoppinglistEntity.DeleteItem;
import com.example.rummates.entities.shoppinglistEntity.Item;
import com.example.rummates.entities.notesEntity.NotesEntity;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.example.rummates.serializer.NotesSerializer;
import com.example.rummates.serializer.ShoppingListSerializer;

public class EndpointController {

    private static EndpointController instance;
    private NotesEndpoint notesEndpoint;
    private ShoppingListEndpoint shoppingListEndpoint;
    private Context context;

    private EndpointController(Context context){
        this.context=context;
        this.notesEndpoint = new NotesEndpoint();
        this.shoppingListEndpoint = new ShoppingListEndpoint();
    }
    public static EndpointController getInstance(Context context) {
        if (instance == null)
            instance = new EndpointController(context);
        return instance;
    }
    public static EndpointController getInstance() {
        return instance;
    }

    public ShoppingListEntity getShoppingListsForGroup(String groupID){
        ShoppingListEntity shoppingListEntity = null;

        try{
            shoppingListEntity = ShoppingListSerializer.shoppingListEntityDeserializer(shoppingListEndpoint.getShoppingLists(groupID));
            Log.d("Info", "Loading data from rumies.herokuapp.com/groups/shopping/"+groupID+".");
            return shoppingListEntity;

        }catch(Exception e){
            Log.d("Info", "Failed to download data(rumies.herokuapp.com/groups/shopping/"+groupID+".");
            return new ShoppingListEntity();
        }
    }

    public void patchShoppingListItem(String groupID, Item item){
        try{
            String JSONitem = ShoppingListSerializer.itemSerialiser(item);
            Log.d("Info", "Item has been serialized");
            shoppingListEndpoint.patchShoppingListItem(groupID, JSONitem);
            Log.d("Info", "Item has been patched http://rumies.herokuapp.com/groups/shopping/item/"+groupID+".");
        }catch(Exception e){
            Log.d("Info", "Failed to patch the item http://rumies.herokuapp.com/groups/shopping/item/"+groupID+".");
        }
    }

    public void patchShoppingListItemComments(String groupID, CommentForItem item){
        try{
            String JSONitem = ShoppingListSerializer.commentForItemSerializer(item);
            Log.d("Info", "Comment has been patched http://rumies.herokuapp.com/groups/shopping/com/"+groupID+".");
            shoppingListEndpoint.patchShoppingListComment(groupID, JSONitem);
        }catch(Exception e){
            Log.d("Info", "Failed to patch the comment http://rumies.herokuapp.com/groups/shopping/com/"+groupID+".");
        }
    }

    public void deleteShoppingListItem(String groupID, DeleteItem item) {
        try {
            String JSONitem = ShoppingListSerializer.deleteItemSerializer(item);
            Log.d("Info", "Item has been serialized");
            shoppingListEndpoint.deleteShoppingListItem(groupID, JSONitem);
            Log.d("Info", "Item has been deleted http://rumies.herokuapp.com/groups/shopping/item/"+groupID+".");
        } catch (Exception e) {
            Log.d("Info", "Failed to delete the item http://rumies.herokuapp.com/groups/shopping/item/"+groupID+".");
        }
    }

    public NotesEntity getNotesForGroup(String groupID){
        NotesEntity notesEntity = null;

        try{
            notesEntity = NotesSerializer.notesEntityDeserializer(notesEndpoint.getNotes(groupID));
            Log.d("Info", "Loading data from https://rumies.herokuapp.com/groups/notes/" + groupID + ".");
            return notesEntity;

        }catch(Exception e){
            Log.d("Info", "Failed to download data from https://rumies.herokuapp.com/groups/notes/" + groupID + ".");
            return new NotesEntity();
        }
    }

    //Note(author, content)
    public void patchNoteForGroup(String groupID, Note note){
        try{
            String JSONnote = NotesSerializer.noteSerializer(note);
            Log.d("Info", "Note has been serialized");
            notesEndpoint.patchNote(groupID, JSONnote);
            Log.d("Info", "Note has been patched https://rumies.herokuapp.com/groups/notes/" + groupID + ".");
        }catch(Exception e){
            Log.d("Info", "Failed to patch the note https://rumies.herokuapp.com/groups/notes/" + groupID + ".");
        }
    }

    //Note(content)
    public void deleteNoteForGroup(String groupID, Note note){
        try{
            String JSONnote = NotesSerializer.noteSerializer(note);
            Log.d("Info", "Note has been serialized");
            notesEndpoint.deleteNote(groupID, JSONnote);
            Log.d("Info", "Note has been deleted https://rumies.herokuapp.com/groups/notes/" + groupID + ".");
        }catch(Exception e){
            Log.d("Info", "Failed to delete the note https://rumies.herokuapp.com/groups/notes/" + groupID + ".");
        }
    }


    public ShoppingListEndpoint getShoppingListEndpoint() {
        return shoppingListEndpoint;
    }

    public NotesEndpoint getNotesEndpoint() {
        return notesEndpoint;
    }
}
