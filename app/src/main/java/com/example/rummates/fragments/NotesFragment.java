package com.example.rummates.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.rummates.EditNoteActivity;
import com.example.rummates.R;
import com.example.rummates.entities.testEntity.PostEntity;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private Context mContext;
    private NotesViewModel notesViewModel;
    private ListView listView;
    private View root;
    private List<String> postsJson;
    private ArrayList<PostEntity> postEntities = new ArrayList<>();
    private ArrayList<String> posts;
    public static ArrayAdapter<String> arrayAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notesViewModel = ViewModelProviders.of(this.getActivity()).get(NotesViewModel.class);

//        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        arrayAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, notesViewModel.getNotes());
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent (getActivity(), EditNoteActivity.class);
                intent.putExtra("postId", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_notes, container, false);
        listView = (ListView) root.findViewById(R.id.postsListView);

//        this.mContext = this.getActivity();
//        postsJson = this.getPostsEntitiesFromFile();
//        posts = new ArrayList<>();
//        for (String postJson : postsJson) {
//            postEntities.add(PostSerializer.singlePostDeserializer(postJson));
//        }
//        for(PostEntity postEntity : postEntities){
//            posts.add(postEntity.getDescription());
//        }
        return root;
    }

//    public List<String> getPostsEntitiesFromFile() {
//        List<String> mLines = new ArrayList<>();
//
//        AssetManager am = mContext.getAssets();
//
//        try {
//            InputStream is = am.open("posts.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            String line;
//            while ((line = reader.readLine()) != null)
//                mLines.add(line);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return mLines;
//    }
}