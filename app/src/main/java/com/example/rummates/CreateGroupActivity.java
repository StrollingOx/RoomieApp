package com.example.rummates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rummates.controllers.EndpointController;
import com.example.rummates.entities.groupEntity.GroupEntity;
import com.example.rummates.serializer.GroupSerializer;

public class CreateGroupActivity extends AppCompatActivity {

    Button newGroup;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        newGroup = (Button)findViewById(R.id.addNewGroup);
        input = (EditText)findViewById(R.id.group_name);

        newGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String xd = GroupSerializer.groupSerializer(new GroupEntity(input.getText().toString()));
                System.out.println(xd);

                GroupEntity groupEntity = new GroupEntity(input.getText().toString());
                EndpointController.getInstance().addNewGroup(groupEntity);
                //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
