package com.example.listados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class Spinner_1_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner1);

        Spinner spPlaneta1 = findViewById(R.id.spPlaneta1);

        //GENERAR EL ESCUCHADOR DEL SPINNER
        spPlaneta1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elementoSeleccionado = spPlaneta1.getSelectedItem().toString();//CASTEAMOS A STRING
                //PODEMOS ACCEDER AL ELEMENTO SELECCIONADO DESDE EL ADAPTADOR
                String elementoSeleccionado2 = parent.getItemAtPosition(position).toString(); //ELIGE EL  ITEM SELECCIONADO
                Toast.makeText(Spinner_1_Activity.this, "Has elegido "+elementoSeleccionado+ "\n"+elementoSeleccionado2, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //no tiene interes para nosotros

            }
        });
    }
}