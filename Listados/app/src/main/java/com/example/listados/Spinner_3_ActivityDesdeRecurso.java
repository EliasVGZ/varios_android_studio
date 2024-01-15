package com.example.listados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Spinner_3_ActivityDesdeRecurso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner3);


        Spinner spPlanetas3 = findViewById(R.id.spPlanetas3);

        //TODO METODO 1 -> createFromResource(), contexto, ubicacion del array, y el layout que genera el IDE
        //ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.planetas, android.R.layout.simple_spinner_dropdown_item);

        //TODO: Metodo 2-> Otra forma de recuperar el array en recurso
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.planetas));

        //asginar el adaptador al spinner
        spPlanetas3.setAdapter(adaptador2);


        spPlanetas3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elementoSeleccionado = spPlanetas3.getSelectedItem().toString();//CASTEAMOS A STRING
                //PODEMOS ACCEDER AL ELEMENTO SELECCIONADO DESDE EL ADAPTADOR
                String elementoSeleccionado2 = parent.getItemAtPosition(position).toString(); //ELIGE EL  ITEM SELECCIONADO
                Toast.makeText(Spinner_3_ActivityDesdeRecurso.this, "Has elegido "+elementoSeleccionado+ "\n"+elementoSeleccionado2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //NO TIENE INTERES PARA NOSOTRS

            }
        });




    }
}