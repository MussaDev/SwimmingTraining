package com.example.swimmingtraining;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Perepiska extends AppCompatActivity {

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
    }
}
