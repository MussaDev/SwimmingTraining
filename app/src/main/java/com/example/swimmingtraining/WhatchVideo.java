package com.example.swimmingtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WhatchVideo extends AppCompatActivity {
    List<UVideo> uvideo;
    ListView listvideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatch_video);
        listvideo = findViewById(R.id.listviewvideo);
        uvideo = new ArrayList<>();
        registerForContextMenu(listvideo);
        DatabaseReference dbvideo = FirebaseDatabase.getInstance().getReference("video");
        dbvideo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UVideo a = postSnapshot.getValue(UVideo.class);
                    uvideo.add(a);
                }

                String[] uploads = new String[uvideo.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uvideo.get(i).getName_video() + "\n";
                }

//displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listvideo.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
