package com.example.listados;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Spinner_5_DinamicoAcitivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spDinamico;
    private Button btn_aceptar;
    private ArrayList<String> arrayListColores;
    private ArrayAdapter<String> adaptador;
    private EditText et_colores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner5_dinamico_acitivity);


        spDinamico = findViewById(R.id.spDinamico);
        btn_aceptar = findViewById(R.id.btn_aceptar);
        et_colores = findViewById(R.id.et_colores);

        btn_aceptar.setOnClickListener(this);
        et_colores.setOnClickListener(this);

        arrayListColores = new ArrayList<>();

        //arrayListColores.add("");
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayListColores);

        spDinamico.setAdapter(adaptador);

        spDinamico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elementoSeleccionado = spDinamico.getSelectedItem().toString();//CASTEAMOS A STRING

                Toast.makeText(Spinner_5_DinamicoAcitivity.this, "Has elegido "+elementoSeleccionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //NO TIENE INTERES PARA NOSOTRS

            }
        });

    }


    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_aceptar:
                String colores = et_colores.getText().toString();
                if(!colores.isEmpty()){
                    if (arrayListColores.contains(colores)) {
                        Toast.makeText(this, "El color ya está en la lista", Toast.LENGTH_SHORT).show();
                    } else {
                        arrayListColores.add(colores);
                        et_colores.setText("");
                        //TODO si no añado un item vacio no me salta el toast en el momento de seleccionar uno, se tiene que actualizar con notifyDataSetChanged()
                        // Notificar al adaptador que los datos han cambiado
                        adaptador.notifyDataSetChanged();
                        spDinamico.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(this, "Color está vacío", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}