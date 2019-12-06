package com.example.rummates.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.R;
import com.example.rummates.entities.groupEntity.GroupGET;

import java.util.ArrayList;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.Viewholder> {

    private final String TAG = "GroupAdapter";

    private ArrayList<GroupGET> arrayGroups;
    private OnItemClickListener groupListener;
    private Context mContext;

    public GroupsAdapter(ArrayList<GroupGET> arrayGroups, Context context) {
        this.arrayGroups = arrayGroups;
        this.mContext = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        groupListener = listener;
    }


    public static class Viewholder extends RecyclerView.ViewHolder{
    TextView groupName;

        public Viewholder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_name);
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_group_item, parent, false);
        return new Viewholder(view, groupListener);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final GroupGET group = arrayGroups.get(position);
        holder.groupName.setText(group.getName());
    }


    @Override
    public int getItemCount() {
        return arrayGroups.size();
    }
}
