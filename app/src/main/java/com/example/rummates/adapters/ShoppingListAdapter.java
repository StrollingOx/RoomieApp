package com.example.rummates.adapters;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.R;
import com.example.rummates.adapters.expandableadapter.CommentAdapter;
import com.example.rummates.adapters.expandableadapter.CommentGroupModel;
import com.example.rummates.classes.Item;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>{

    private ArrayList<Item> arrayItems;
    private OnItemClickListener sliListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        //void onDeleteClick(int position);
        //void onSomethingOtherClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        sliListener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sliName, sliMenu;
        CheckBox sliCheckBox;
        RecyclerView sliCommentsRV;


        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            sliName = itemView.findViewById(R.id.sli_name);
            sliCheckBox = itemView.findViewById(R.id.sli_checkbox);
            sliMenu  = itemView.findViewById(R.id.sli_menu);
            sliCommentsRV = itemView.findViewById(R.id.sli_recyclerview);
            sliCommentsRV.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

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


    public ShoppingListAdapter(ArrayList<Item> itemList) {
        this.arrayItems = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shoppinglist_item, parent, false);
        return new ViewHolder(view, sliListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position){ //final int position{
        final Item currentItem = arrayItems.get(position);

        holder.sliName.setText(currentItem.getItemName());
        holder.sliCheckBox.setChecked(currentItem.isChecked());
        holder.sliCheckBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentItem.toggle();
            }
        });
        holder.sliMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v); //hmmmmmmmmmmm
                popup.inflate(R.menu.layout_shoppinglist_item_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.slim_add_comment:
                                //handle menu1 click
                                return true;
                            case R.id.slim_delete:
                                arrayItems.remove(position);
                                notifyItemRemoved(position);
                                return true;
                            case R.id.slim_create_notification:
                                //handle menu3 click
                                return true;
                            case R.id.slim_details:
                                //handle menu4 click
                                return true;
                            default:
                                return false;
                    }
                }}); popup.show();
            }
        });

        //comments
        ArrayList<CommentGroupModel> comments = new ArrayList<>();
        CommentGroupModel cgm = new CommentGroupModel("Comments", currentItem.getComments());
        comments.add(cgm);

        CommentAdapter commentAdapter = new CommentAdapter(comments);
        holder.sliCommentsRV.setAdapter(commentAdapter);

    }



    @Override
    public int getItemCount() {
        return arrayItems.size();
    }

}
