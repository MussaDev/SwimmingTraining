package com.example.swimmingtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ZaprosTrainer extends AppCompatActivity {

    //the listview
    ListView listViewListZapros;

    //database reference to get uploads data
    DatabaseReference dbrol;

    //list to store uploads data
    List<UZapros> uzapros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapros_trainer);
        uzapros = new ArrayList<>();

        listViewListZapros = (ListView) findViewById(R.id.list_zapros);
        registerForContextMenu(listViewListZapros);

//adding a clicklistener on listview
        listViewListZapros.setOnCreateContextMenuListener(new AdapterView.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                ZaprosTrainer.super.onCreateContextMenu(menu, v, menuInfo);
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.context_zapros, menu);

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            }
        });

        //getting the database reference
        DatabaseReference dbzapros = FirebaseDatabase.getInstance().getReference("Zapros");
        DatabaseReference dbuid = dbzapros.child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        //retrieving upload data from firebase database
        dbuid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UZapros pzapros = postSnapshot.getValue(UZapros.class);
                    uzapros.add(pzapros);
                }

                String[] uplist = new String[uzapros.size()];

                for (int i = 0; i < uplist.length; i++) {
                    uplist[i] = uzapros.get(i).getOt() + "\n";
                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uplist);
                listViewListZapros.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
