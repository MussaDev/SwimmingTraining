package com.example.swimmingtraining;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    public String vname;
    public String vfamailia;
    public String votchestvo;
    public String vdr;
    public String vlogin;
    public String vemail;
    public String vraiting;
    List<UZapros> uzapros;

    DatabaseReference dbuserst = FirebaseDatabase.getInstance().getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapros_trainer);
        uzapros = new ArrayList<>();
        listViewListZapros = (ListView) findViewById(R.id.list_zapros);
        registerForContextMenu(listViewListZapros);

        //подключение к БД
        DatabaseReference dbzapros = FirebaseDatabase.getInstance().getReference("Zapros");
        DatabaseReference dbuid = dbzapros.child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        //Чтение списка
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

        //контекстное меню
        listViewListZapros.setOnCreateContextMenuListener(new AdapterView.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                ZaprosTrainer.super.onCreateContextMenu(menu, v, menuInfo);
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.context_zapros, menu);

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                position = info.position;


            }
        });
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.yes:

                //Получение uid пользователя
                String selectedFromList = (listViewListZapros.getItemAtPosition(position)).toString();
                String[] parts = selectedFromList.split("\n");
                String part0 = parts[0];

                //Очтистка запроса
                DatabaseReference dbzapros = FirebaseDatabase.getInstance().getReference("Zapros");
                DatabaseReference dbuid = dbzapros.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                dbuid.setValue(" ");

                //Поиск пользователя по uid в БД
                DatabaseReference dbpart = dbuserst.child(part0);

                //запись данных в UAdd
                UAdd uadd = new UAdd(vname,vfamailia, votchestvo, vdr, vlogin, vemail, vraiting);

                //Запись UAdd в бд тренера
                DatabaseReference dbuidtr = dbuserst.child(FirebaseAuth.getInstance().getCurrentUser().getUid()); //ветка тренера
                DatabaseReference dbtreainer_sportsman = dbuidtr.child("sportsman"); //ветка спортсменов у тренера
                DatabaseReference dbtrainer_sportsman_uid = dbtreainer_sportsman.child(part0); //ветка спортсмена у тренера
                dbtrainer_sportsman_uid.setValue(uadd);

                //чтение данных о пользователе
                dbpart.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        vname = dataSnapshot.child("name").getValue(String.class);
                        vfamailia = dataSnapshot.child("familia").getValue(String.class);
                        votchestvo = dataSnapshot.child("otchestvo").getValue(String.class);
                        vdr = dataSnapshot.child("dr").getValue(String.class);
                        vlogin = dataSnapshot.child("login").getValue(String.class);
                        vemail = dataSnapshot.child("email").getValue(String.class);
                        vraiting = dataSnapshot.child("rating").getValue(String.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("Failed to read value.", error.toException());
                    }
                });

                //Запись UAdd в бд тренера
                dbtrainer_sportsman_uid.setValue(uadd);

                Toast.makeText(getApplicationContext(),"Выполнено",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.no:
                Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}