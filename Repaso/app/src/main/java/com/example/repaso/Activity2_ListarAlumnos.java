package com.example.repaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2_ListarAlumnos extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    private ArrayList<String> listaAlumnos;
    private ArrayList<Alumnos> listadoAlumnos;

    private ListView listView;
    private MiClaseParaBBDD miClase;
    private static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_listar_alumnos);

        listView = findViewById(R.id.listView);
        listadoAlumnos = new ArrayList<>();
        listaAlumnos = getIntent().getStringArrayListExtra("lista_alumnos");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAlumnos);

        listView.setAdapter(adapter);


    }






}