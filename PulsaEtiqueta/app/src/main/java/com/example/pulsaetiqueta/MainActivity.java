package com.example.pulsaetiqueta;


import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSaludo = findViewById(R.id.tvSaludo);

        tvSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setRotation(view.getRotation() +45);//rotar 45 grados
                tvSaludo.setTextColor(Color.BLUE);
                tvSaludo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
                tvSaludo.setBackgroundColor(Color.GREEN);
            }
        });
    }
}