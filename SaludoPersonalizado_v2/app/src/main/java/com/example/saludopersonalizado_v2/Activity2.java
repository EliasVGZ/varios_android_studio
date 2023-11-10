package com.example.saludopersonalizado_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btnFinalizar = findViewById(R.id.btnFinalizar);

        Intent intent = getIntent();
        //recuperamos el objeto bundle que se le ha pasado con el intent
        Bundle bundle = intent.getExtras();

        //RECUPERAR DATO CON EL METODO ADECUADO
        String mensajeRecibido = bundle.getString("mensaje");

        //EN UNA SOLA SENTENCIA
        String datoRecibido=getIntent().getExtras().getString("mensaje");

        TextView tvRecibida=findViewById(R.id.tvMensajeRecibidoAct2);
        tvRecibida.setText(mensajeRecibido);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
    }
}