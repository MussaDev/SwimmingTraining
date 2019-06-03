package com.example.swimmingtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AdminPanel extends AppCompatActivity {

    EditText e_name_video, e_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        e_name_video = findViewById(R.id.name_video);
        e_url = findViewById(R.id.url);

    }

    public void add(View view) {
        String s_name_vedeo = e_name_video.getText().toString();
        String s_url = e_url.getText().toString();
        UVideo uvidik = new UVideo(s_name_vedeo,s_url);
        FirebaseDatabase.getInstance()
                .getReference()
                .child("video")
                .push()
                .setValue(uvidik);
        Toast.makeText(getApplicationContext(),"Видео добавлено",Toast.LENGTH_SHORT).show();
    }
}
