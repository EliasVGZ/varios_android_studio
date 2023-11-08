package com.example.activities_intent_ejemplos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        Intent intent = getIntent();
        //recuperamos el objeto bundle que se le ha pasado con el intent
        Bundle bundle = intent.getExtras();

        //RECUPERAR DATO CON EL METODO ADECUADO
        String mensajeRecibido = bundle.getString("mensaje");

        //EN UNA SOLA SENTENCIA
        String datoRecibido2=getIntent().getExtras().getString("mensaje");

        TextView tvRecibida=findViewById(R.id.tvMensajeRecibidoAct4);
        tvRecibida.setText(mensajeRecibido);


    }
}