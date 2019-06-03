package com.example.swimmingtraining;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_login extends AppCompatActivity {

    EditText login, passw;
    String datalogin, datapassw;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        login = findViewById(R.id.lo);
        passw = findViewById(R.id.pa);

        DatabaseReference dbadmin = database.getReference("admin");

        DatabaseReference dblogin = dbadmin.child("login");
        dblogin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                datalogin = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

        DatabaseReference dbpassw = dbadmin.child("passw");
        dbpassw.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                datapassw = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });
    }

    public void go(View view) {
        String slogin = login.getText().toString();
        String spassw = passw.getText().toString();
        if (datalogin.equals(slogin) && datapassw.equals(spassw)) {
            Intent intent = new Intent(Admin_login.this, AdminPanel.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(),"Неверный логин или пароль.",Toast.LENGTH_SHORT).show();
        }
    }
}
