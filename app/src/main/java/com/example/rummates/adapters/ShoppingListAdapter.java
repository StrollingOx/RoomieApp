package com.example.rummates.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.R;
import com.example.rummates.classes.Item;

import java.util.ArrayList;

//TODO: comments on item (maybe as recyclerView?)
//TODO: menu on item

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
        TextView sliName;
        CheckBox sliCheckBox;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            sliName = itemView.findViewById(R.id.sli_name);
            sliCheckBox = itemView.findViewById(R.id.sli_checkbox);

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
        Item currentItem = arrayItems.get(position);

        holder.sliName.setText(currentItem.getItemName());
        holder.sliCheckBox.setChecked(currentItem.isChecked());

    }

    @Override
    public int getItemCount() {
        return arrayItems.size();
    }

}
