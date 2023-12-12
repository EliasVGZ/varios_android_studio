package com.example.a2023_eliasvierafernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{

    private ImageButton img_logo;
    private LinearLayout ll1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        img_logo = findViewById(R.id.img_logo);


        img_logo.setOnLongClickListener(this);

    }



    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.img_logo){
            Intent intent = new Intent(this, Activity2.class);
            startActivity(intent);
        }

        return false;
    }
}