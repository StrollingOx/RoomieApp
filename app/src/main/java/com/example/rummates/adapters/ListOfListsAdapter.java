package com.example.rummates.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.MainActivity;
import com.example.rummates.R;
import com.example.rummates.dialogs.AddCommentDialog;
import com.example.rummates.entities.shoppinglistEntity.ShoppingList;

import java.util.ArrayList;

public class ListOfListsAdapter extends RecyclerView.Adapter<ListOfListsAdapter.ViewHolder>{

    private final String TAG = "ShoppingListAdapter";

    String groupID;

    private ArrayList<ShoppingList> shoppingLists;
    private OnItemClickListener listsListener;
    private Context mContext;

    public ListOfListsAdapter(ArrayList<ShoppingList> shoppingList, Context context, String groupID) {
        this.shoppingLists = shoppingList;
        this.mContext = context;
        this.groupID = groupID;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        //void onDeleteClick(int position);
        //void onSomethingOtherClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        listsListener = listener;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sliName;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            sliName = itemView.findViewById(R.id.sli_name2);

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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_of_lists_item,
                parent, false);
        return new ViewHolder(view, listsListener);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position){ //final int position{
        final ShoppingList currentList = shoppingLists.get(position);
        holder.sliName.setText(currentList.getListName());
    }



    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

//    private void openAddCommentDialog(int position){
//        AddCommentDialog addCommentDialog = new AddCommentDialog(position, groupID, username);
//        addCommentDialog.show(((MainActivity)mContext).getSupportFragmentManager(), "dialog");
//    }
}
