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

public class ListSportsman extends AppCompatActivity {
    //the listview
    ListView listViewListSportsman;

    //database reference to get uploads data
    DatabaseReference dbrol;

    //list to store uploads data
    List<UList> ulist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sportsman);
            ulist = new ArrayList<>();
            listViewListSportsman = findViewById(R.id.list_sportsman);

            //getting the database reference
            DatabaseReference dbrol = FirebaseDatabase.getInstance().getReference("rol");
            DatabaseReference dbsportsman = dbrol.child("sportsman");

    //retrieving upload data from firebase database
            dbsportsman.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        UList plist = postSnapshot.getValue(UList.class);
                        ulist.add(plist);
                    }

                    String[] uplist = new String[ulist.size()];

                    for (int i = 0; i < uplist.length; i++) {
                        uplist[i] = ulist.get(i).getFam() + " " + ulist.get(i).getIm() + " " + ulist.get(i).getOtch() + "\n";
                    }

    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uplist);
                    listViewListSportsman.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
}