package com.example.swimmingtraining;

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

public class ZaprosTrainer extends AppCompatActivity {

    //объявление переменных
    ListView listViewListZapros;
    public int position;
    List<UZapros> uzapros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapros_trainer);
        uzapros = new ArrayList<>();
        listViewListZapros = (ListView) findViewById(R.id.list_zapros);
        registerForContextMenu(listViewListZapros);

//элементы списка
        listViewListZapros.setOnCreateContextMenuListener(new AdapterView.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                ZaprosTrainer.super.onCreateContextMenu(menu, v, menuInfo);
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.context_zapros, menu);

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            }
        });

        //Чтение списка
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
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.addtrainer:
                String selectedFromList = (listViewListZapros.getItemAtPosition(position)).toString();
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