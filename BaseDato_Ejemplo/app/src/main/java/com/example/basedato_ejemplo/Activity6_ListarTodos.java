package com.example.basedato_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity6_ListarTodos extends AppCompatActivity {

    private TextView tv_consulta_usuario;
    private static SQLiteDatabase db;
    private MiClaseParaBBDD miClase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity6_listar_todos);

        ListView listView = findViewById(R.id.listView);
        ArrayList<String> listaUsuarios = getIntent().getStringArrayListExtra("lista_usuarios");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaUsuarios);
        listView.setAdapter(adapter);
    }
}