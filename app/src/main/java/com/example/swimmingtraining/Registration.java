package com.example.swimmingtraining;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText email,password,familia,name,otchestvo,dr;
    Button registerButton,loginButton;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        familia = (EditText) findViewById(R.id.familia);
        name = (EditText) findViewById(R.id.name);
        otchestvo = (EditText) findViewById(R.id.otchestvo);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.passw);
        dr = (EditText) findViewById(R.id.dr);
        //dr = (Spinner) findViewById(R.id.dr);

        registerButton = (Button) findViewById(R.id.reg);
        loginButton = (Button) findViewById(R.id.vhod);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void reg(View view) {
        String famalia1 = familia.getText().toString();
        String name1 = name.getText().toString();
        String otchestvo1  = otchestvo.getText().toString();
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();
        String dr1 = dr.getText().toString();
        familia.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        name.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        otchestvo.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        email.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        password.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        dr.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);


        if(TextUtils.isEmpty(famalia1)){
            familia.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(name1)){
            name.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(otchestvo1)){
            otchestvo.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email1)){
            email.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password1)){
            password.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(dr1)){
            dr.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email1,password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),Main_trainer.class));
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Данный E-mail уже зарегистрирован",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
