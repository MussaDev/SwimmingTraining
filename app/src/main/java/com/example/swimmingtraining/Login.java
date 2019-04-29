package com.example.swimmingtraining;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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


public class Login extends AppCompatActivity {
    EditText email,password;
    Button registerButton,loginButton;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        registerButton = (Button) findViewById(R.id.vhod);
        loginButton = (Button) findViewById(R.id.reg);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                if(TextUtils.isEmpty(email1)){
                    Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
                    return;
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
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                if(TextUtils.isEmpty(email1)){
                    Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password1)){
                    Toast.makeText(getApplicationContext(),"Пожалуйста заполните необходимые поля",Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email1,password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(getApplicationContext(),Main_trainer.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"E-mail или пароль не правильны",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),Main_trainer.class));
        }
    }
}