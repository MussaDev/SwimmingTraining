package com.example.swimmingtraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText Login,Passw;
    //TextView Error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void go(View view) {
//        Intent intent = new Intent(Login.this, Main_trainer.class);
//        startActivity(intent);.
        final EditText login = (EditText)findViewById(R.id.login);
        final EditText password = (EditText)findViewById(R.id.password);
        if(login.getText().toString().equals("test") && password.getText().toString().equals("test")) {
            Intent intent = new Intent(Login.this, Main_trainer.class);
            startActivity(intent);
        }
        else {
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("Неверный логин или пароль");
        }

    }


}
