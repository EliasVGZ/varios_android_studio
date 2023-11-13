package com.example.activities_intent_ejemplos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity6 extends AppCompatActivity {

    private Button btnRetornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        btnRetornar = findViewById(R.id.btnVolver);

        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //volver a la actividad 1 enviandole algo
                String datoRespuesta = "La actividad 6666666 envia de vuelta este mensaje a la act1";
                Intent intent = new Intent();
                intent.putExtra("respuesta", datoRespuesta);
                setResult(RESULT_OK, intent);
                Log.i("CICLO", "EJECUTANDO LISTENER");
                //DESTRUIR PARA QUE NO QUEDE EN LA PILA
                finish();

            }
        });







    }
}