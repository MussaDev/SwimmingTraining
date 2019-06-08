package com.example.swimmingtraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GoustMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goust_menu);
    }

    public void listtrainer(View view) {
        Intent intent = new Intent(GoustMenu.this, ListTrainerToGoust.class);
        startActivity(intent);
    }

    public void whatch_video(View view) {
        Intent intent = new Intent(GoustMenu.this, WhatchVideo.class);
        startActivity(intent);
    }
}
