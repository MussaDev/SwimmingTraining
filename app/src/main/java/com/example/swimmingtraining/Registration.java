package com.example.swimmingtraining;

import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText email,password,familia;
    Button registerButton,loginButton;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.passw);
        familia = (EditText) findViewById(R.id.familia);
        registerButton = (Button) findViewById(R.id.reg);
        loginButton = (Button) findViewById(R.id.vhod);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void reg(View view) {
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();
        String famalia1 = familia.getText().toString();

        if(TextUtils.isEmpty(email1)){
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password1)){
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(famalia1)){
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password1)){
            Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
        }

        if(password1.length()<6){
            Toast.makeText(getApplicationContext(),"Пароль должен содержать не менее 6 символов",Toast.LENGTH_SHORT).show();
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
