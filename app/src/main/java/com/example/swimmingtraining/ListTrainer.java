package com.example.swimmingtraining;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListTrainer extends AppCompatActivity {
    //the listview
    ListView listViewListTraineer;

    //database reference to get uploads data
    DatabaseReference dbrol;

    //list to store uploads data
    List<UList> ulist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trainer);
        ulist = new ArrayList<>();

        listViewListTraineer = (ListView) findViewById(R.id.list_trainer);
        registerForContextMenu(listViewListTraineer);

//adding a clicklistener on listview
        listViewListTraineer.setOnCreateContextMenuListener(new AdapterView.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                ListTrainer.super.onCreateContextMenu(menu, v, menuInfo);
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.context_menu_trainer, menu);

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            }
        });

        //getting the database reference
        DatabaseReference dbrol = FirebaseDatabase.getInstance().getReference("rol");
        DatabaseReference dbsportsman = dbrol.child("trainer");

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
                listViewListTraineer.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listViewListTraineer.setOnCreateContextMenuListener(new AdapterView.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                ListTrainer.super.onCreateContextMenu(menu, v, menuInfo);
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.context_menu_trainer, menu);

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                int position = info.position;


            }
        });
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.addtrainer:

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}