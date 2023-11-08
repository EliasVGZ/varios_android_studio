package com.example.activities_intent_ejemplos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        //RECUPERAR EL DATO ENVIADO DESDE EL ACTIVITY 1 MEDIANTE EL INTENT
        Intent myintent = getIntent();
        //EXTRAER EL DATO
        String datoRecibido = myintent.getStringExtra("mensaje");
        //VISUALIZARLO EN LA TEXTVIEW
        TextView tvRecepcion = findViewById(R.id.tvMensajeRecibido);
        tvRecepcion.setText(datoRecibido);

    }
}