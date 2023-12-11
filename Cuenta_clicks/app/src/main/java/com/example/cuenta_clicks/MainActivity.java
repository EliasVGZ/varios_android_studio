package com.example.cuenta_clicks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
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

        // Bloquear la orientación a retrato (vertical)
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
                tvPulsar.setText("Contador vuelve a "+contador);
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


    //Se invoca para permitir a la actividad guardar su
    //estado.
    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("contador", contador);
        Log.i("ciclo", "Actividad 1 - Ejecutando onSaveInstanceState");
    }


    //Se invoca para recuperar el estado guardado
    //por onSaveInstanceState().
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contador = savedInstanceState.getInt("contador");
        Log.i("ciclo", "Actividad 1 - Ejecutando onRestoreInstanceState");
        if (contador == 1){
            tvPulsar.setText("Has pulsado " + contador + " vez.");
        } else {
            tvPulsar.setText("Has pulsado " + contador + " veces.");
        }
    }

    //TODO PARA BLOQUEAR LA ORIENTACION DEL MOVIL

    /**
     * En el archivo AndroidManifest, dentro de la actividad, mediante una línea de
     * código como las siguientes:
     * android:screenOrientation="portrait"
     * android:screenOrientation="landscape"
     *  Mediante código Java:
     * setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
     * setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
     */


}