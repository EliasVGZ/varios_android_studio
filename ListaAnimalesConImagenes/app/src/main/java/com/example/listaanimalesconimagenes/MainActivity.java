package com.example.listaanimalesconimagenes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView lv_animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_animales = findViewById(R.id.lv_animales);
        
        AdaptadorPersonalizado_Animales adaptador = new AdaptadorPersonalizado_Animales(
                this,
                R.layout.layout_personalizado_animales,
                getResources().getStringArray(R.array.animales),
                getResources().obtainTypedArray(R.array.fotos_animales),
                getResources().getStringArray(R.array.info),
                getResources().obtainTypedArray(R.array.colores)
        );
        
        lv_animales.setAdapter(adaptador);

        lv_animales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String animalSeleccionado = Arrays.toString(getResources().getStringArray(R.array.animales));
                String infoAnimal = getResources().getStringArray(R.array.info).toString();

                Log.d("MainActivity", "Item clicked at position: " + position);

                Toast.makeText(MainActivity.this, "" + animalSeleccionado + "\n" + infoAnimal, Toast.LENGTH_SHORT).show();
            }
        });

    }
}