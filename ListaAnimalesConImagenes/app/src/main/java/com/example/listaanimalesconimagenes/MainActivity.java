package com.example.listaanimalesconimagenes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends Auxiliar_Activity {

    private ListView lv_animales;
    private String animales;

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
                String animal = parent.getItemAtPosition(position).toString();
                String info = getResources().getStringArray(R.array.info)[position];//COLOCAR LA INFO DE CADA ANIMAL



                Toast.makeText(MainActivity.this, "ANIMAL: " + animal + "\nINFO: " + info, Toast.LENGTH_SHORT).show();
            }
        });
    }
}