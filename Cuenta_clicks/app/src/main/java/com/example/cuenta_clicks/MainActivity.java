package com.example.cuenta_clicks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvPulsar;
    private Button btnPulsar, btnFinalizar;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPulsar= findViewById(R.id.tvPulsar); //busca por el id

        btnPulsar = findViewById(R.id.btnPulsar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        //SETE EL BOTON ESCUCHADORPULSAR
        btnPulsar.setOnClickListener(escuchadorPulsar);

        //METODO 3 PARA BOTON FINALIZAR
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador = 0;
                tvPulsar.setText("Contador vuelve a "+contador);new
            }
        });



    }


    //METODO 2 PARA BOTON PULSAR
    private View.OnClickListener escuchadorPulsar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            contador++;
            if (contador == 1){
                tvPulsar.setText("Haz pulsado "+contador+ " vez");
            }else if (contador > 1){
                tvPulsar.setText("Haz clickeado "+contador+" veces");
            }

        }
    };
}