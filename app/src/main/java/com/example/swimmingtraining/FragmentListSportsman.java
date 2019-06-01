package com.example.swimmingtraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentListSportsman extends AppCompatActivity {

    //the listview
    ListView listViewListSportsman;

    //database reference to get uploads data
    DatabaseReference dbrol;

    //list to store uploads data
    List<Upload> uploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_sportsman);

        uploadList = new ArrayList<>();
        listViewListSportsman = (ListView) findViewById(R.id.listSportsman);


//adding a clicklistener on listview
        listViewListSportsman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Upload upload = Upload.get(i);

                startActivity(new Intent(getApplicationContext(),ReedBook.class));
            }
        });
//getting the database reference
        rol = FirebaseDatabase.getInstance().getReference("rol");

//retrieving upload data from firebase database
        mDatabaseReferenceDesiredBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadDesiredBook uploadDesiredBook = postSnapshot.getValue(UploadDesiredBook.class);
                    uploadListDesiredBook.add(uploadDesiredBook);
                }

                String[] uploadsDesiredBook = new String[uploadListDesiredBook.size()];

                for (int i = 0; i < uploadsDesiredBook.length; i++) {
                    uploadsDesiredBook[i] = "Название книги: " +uploadListDesiredBook.get(i).getFileName() + "\n"
                            + "Имя автора: " +uploadListDesiredBook.get(i).getAuthorName() + "\n"
                            + "Год издания: " +uploadListDesiredBook.get(i).getYear() + "\n"
                            + "Жанр: " +uploadListDesiredBook.get(i).getGenre() + "\n";
                }

//displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploadsDesiredBook);
                listViewDesiredBook.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        backToDesiredBook = (Button) findViewById(R.id.backToDesiredBook);

        backToDesiredBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LK.class));
            }
        });
    }
}