package com.example.swimmingtraining;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Balance extends AppCompatActivity {

    TextView tv_balance;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference user = database.getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        tv_balance = findViewById(R.id.balance);

        //чтение с бд
        DatabaseReference uid = user.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        uid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valuebalance = dataSnapshot.child("balance").getValue(String.class);
                tv_balance.setText(valuebalance);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });
    }
}
