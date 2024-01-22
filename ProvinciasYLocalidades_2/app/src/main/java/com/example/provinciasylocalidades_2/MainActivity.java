package com.example.provinciasylocalidades_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView ac_provincia;
    private Spinner spLocalidad;

    ArrayAdapter<String> localidadAdapter;

    ArrayAdapter<String> adaptador;
    private String provinciaSeleccionada;

    private String[] arrayLocalidades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ac_provincia = findViewById(R.id.ac_provincia);
        spLocalidad = findViewById(R.id.spLocalidad);

        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.provincias));
        ac_provincia.setAdapter(adaptador);

        ac_provincia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                provinciaSeleccionada = parent.getItemAtPosition(position).toString();

                // Inicializa arrayLocalidades como un array vacío
                arrayLocalidades = new String[0];

                switch (provinciaSeleccionada) {
                    case "Pontevedra":
                        arrayLocalidades = getResources().getStringArray(R.array.localidadPontevedra);
                        break;
                    case "A Coruña":
                        arrayLocalidades = getResources().getStringArray(R.array.localidadCoruña);
                        break;
                    case "Ourense":
                        arrayLocalidades = getResources().getStringArray(R.array.localidadOurense);
                        break;
                    case "Lugo":
                        arrayLocalidades = getResources().getStringArray(R.array.localidadLugo);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Aun no se han definido localidades", Toast.LENGTH_SHORT).show();

                }

                // adaptador para el segundo Spinner (localidades)
                localidadAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, arrayLocalidades);
                spLocalidad.setAdapter(localidadAdapter);
            }
        });


        spLocalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // localidad seleccionada
                String localidadSeleccionada = parent.getItemAtPosition(position).toString();

                String mensaje = "Provincia: " + provinciaSeleccionada + ", Localidad: " + localidadSeleccionada;
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}

