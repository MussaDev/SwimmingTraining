package com.example.swimmingtraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMessageSportsman extends Fragment implements View.OnClickListener {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message_sportsman, container, false);
        Button upButton = (Button) view.findViewById(R.id.button3);
        upButton.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(),Perepiska.class);
        startActivity(intent);
    }
}

