package com.example.animales_arraylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv_animales_arraylist;

    private ArrayList<Animales> listaAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_animales_arraylist = findViewById(R.id.lv_animales_arraylist);
        listaAnimales = new ArrayList<>();


        int longitud = getResources().getStringArray(R.array.animales).length;

        for (int i = 0; i < longitud; i++) {
            listaAnimales.add(new Animales(
                    getResources().getStringArray(R.array.animales)[i],
                    getResources().obtainTypedArray(R.array.fotos_animales).getResourceId(i, -1),
                    getResources().getStringArray(R.array.info)[i],
                    getResources().obtainTypedArray(R.array.colores).getResourceId(i, -1)));

        }


        //Crear instancia del adaptador personalizado
        Adaptador_Personalizado_Animales_ArrayList adaptador = new Adaptador_Personalizado_Animales_ArrayList(
                this, R.layout.layout_personalizado_animales, listaAnimales
        );
        lv_animales_arraylist.setAdapter(adaptador);

        lv_animales_arraylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreAnimal = listaAnimales.get(position).getNombreAnimales();
                String infoAnimal = listaAnimales.get(position).getInfo();
                Toast.makeText(MainActivity.this, ""+nombreAnimal+" \n"+infoAnimal, Toast.LENGTH_SHORT).show();
            }
        });

    }













}