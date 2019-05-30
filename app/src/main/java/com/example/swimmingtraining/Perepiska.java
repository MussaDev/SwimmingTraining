package com.example.swimmingtraining;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Perepiska extends AppCompatActivity {
    //the listview
    ListView listView;

    //database reference to get uploads data
    DatabaseReference mDatabaseReference;

    //list to store uploads data
    List<Chat> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perepiska);
        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input1 = (EditText)findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                Chat chatik = new Chat(input1.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid(),"Vasia");
                FirebaseDatabase.getInstance()
                        .getReference()
                        .child("message")
                        .push()
                        .setValue(chatik);

                // Clear the input
                input1.setText("");
            }
        });
        chatList = new ArrayList<>();
        listView = findViewById(R.id.list_of_messages);

        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("message");

//retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Chat chat = postSnapshot.getValue(Chat.class);
                    chatList.add(chat);
                }

                String[] uploads = new String[chatList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = "Сообщение " +chatList.get(i).getSms() + "\n"
                            + "От: " +chatList.get(i).getOt() + "\n"
                            + "к: " +chatList.get(i).getK() + "\n";
                }

//displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}