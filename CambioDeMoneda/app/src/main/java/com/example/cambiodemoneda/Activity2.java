package com.example.cambiodemoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String mensajeMoneda = bundle.getString("mensajeMoneda");
        TextView tvRecibida = findViewById(R.id.tvMensajeRecibido);
        tvRecibida.setText(mensajeMoneda);





    }
}