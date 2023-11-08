package com.example.activities_intent_ejemplos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity5 extends AppCompatActivity {

    private Button btnRetornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        Log.i("CICLO", "EJECUTANDO OnCreate ACTIVITY 5");

        btnRetornar = findViewById(R.id.btnVolver);

        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //volver a la actividad 1 enviandole algo
                String datoRespuesta = "ESTE ES EL DATO DE RESPUESTA de la activity 5";
                Intent intent = new Intent();
                intent.putExtra("respuesta", datoRespuesta);
                setResult(RESULT_OK, intent);
                Log.i("CICLO", "EJECUTANDO LISTENER");
                //DESTRUIR PARA QUE NO QUEDE EN LA PILA
                finish();

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CICLO", "EJECUTANDO OnStart ACTIVITY 5");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CICLO", "EJECUTANDO OnStop ACTIVITY 5");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLO", "EJECUTANDO OnResume EN ACTIVITY 5");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CICLO", "EJECUTANDO OnDestroy EN ACTIVITY 5");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CICLO", "EJECUTANDO OnRestart EN ACTIVITY 5");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CICLO", "EJECUTANDO OnPause EN ACTIVITY 5");
    }

}