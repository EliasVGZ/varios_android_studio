package com.example.provinciasylocalidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spProvincia, spLocalidad;
    private String[] arrayLocalidades;
    ArrayAdapter<CharSequence> adaptadorProvincias;
    private String provinciaSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spProvincia = findViewById(R.id.spProvincia);
        spLocalidad = findViewById(R.id.spLocalidad);



        adaptadorProvincias = ArrayAdapter.createFromResource(this, R.array.provincias, android.R.layout.simple_spinner_dropdown_item);

        spProvincia.setAdapter(adaptadorProvincias);

        spProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provinciaSeleccionada = spProvincia.getSelectedItem().toString();//CASTEAMOS A STRING

                //SELECCIONA LA POSICION DENTRO DEL ARRAY PROVINCIA Y SE LE ASIGNA LOS OTROS ARRAYS(localidades) A ESA PROVINCIA
                switch (spProvincia.getSelectedItemPosition()) {
                    case 0:
                        arrayLocalidades = getResources().getStringArray(R.array.localidadPontevedra);
                        break;
                    case 1:
                        arrayLocalidades = getResources().getStringArray(R.array.localidadCoruña);
                        break;
                    case 2:
                        arrayLocalidades = getResources().getStringArray(R.array.localidadOurense);
                        break;
                    case 3:
                        arrayLocalidades = getResources().getStringArray(R.array.localidadLugo);
                        break;
                }

                //TODO, POR QUÉ ESTE NO FUNCIONABA????????
                //ArrayAdapter<CharSequence> adaptadorLocalidad = ArrayAdapter.createFromResource(MainActivity.this, arrayLocalidades, android.R.layout.simple_spinner_item);

                // Configura un adaptador para el segundo Spinner (localidades)
                ArrayAdapter<String> localidadAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, arrayLocalidades);
                spLocalidad.setAdapter(localidadAdapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spLocalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String localidad = spLocalidad.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "Provincia: "+provinciaSeleccionada+ "\n"+ "Localidad: "+localidad, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}