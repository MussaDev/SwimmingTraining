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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class ListTrainer extends AppCompatActivity {
    //the listview
    ListView listViewListTraineer;
    public int position;
    //database reference to get uploads data
    DatabaseReference dbrol;

    FirebaseAuth firebaseAuth;
    //list to store uploads data
    List<UList> ulist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trainer);
        ulist = new ArrayList<>();
        listViewListTraineer = (ListView) findViewById(R.id.list_trainer);
        registerForContextMenu(listViewListTraineer);

        //Подключение к бд
        DatabaseReference dbrol = FirebaseDatabase.getInstance().getReference("rol");
        DatabaseReference dbsportsman = dbrol.child("trainer");

        //Вывод списка
        dbsportsman.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UList plist = postSnapshot.getValue(UList.class);
                    ulist.add(plist);
                }

                String[] uplist = new String[ulist.size()];

                for (int i = 0; i < uplist.length; i++) {
                    uplist[i] = ulist.get(i).getFam() + " " + ulist.get(i).getIm() + " " + ulist.get(i).getOtch() + "\n"+ ulist.get(i).getUidik();
                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uplist);
                listViewListTraineer.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //контекстное меню
        listViewListTraineer.setOnCreateContextMenuListener(new AdapterView.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                ListTrainer.super.onCreateContextMenu(menu, v, menuInfo);
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.context_menu_trainer, menu);

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                position = info.position;


            }
        });
    }

    //Дествие контектсного меню
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.addtrainer:
                String selectedFromList = (listViewListTraineer.getItemAtPosition(position)).toString();
                String[] parts = selectedFromList.split("\n");
                String part1 = parts[1]; // UID книги
                String vuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                Toast.makeText(getApplicationContext(),vuid,Toast.LENGTH_SHORT).show();
                UZapros uzapros = new UZapros(vuid,part1);

                DatabaseReference dbzapros = FirebaseDatabase.getInstance().getReference("Zapros");
                DatabaseReference dbtraineer = dbzapros.child(part1);
                dbtraineer
                        .child(vuid)
                        .setValue(uzapros);
//                Toast.makeText(getApplicationContext(),firebaseAuth.getUid(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Запрос отправлен",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}