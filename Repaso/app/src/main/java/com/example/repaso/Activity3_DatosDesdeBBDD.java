package com.example.repaso;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity3_DatosDesdeBBDD extends AppCompatActivity {

    private ListView lv_personal;
    private MiClaseParaBBDD miClase;
    private static SQLiteDatabase db;
    ArrayList<Alumnos> listadoAlumnos;
    private Adaptador_Personalizado adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3_datos_desde_bbdd);

        lv_personal = findViewById(R.id.lv_personal);
        miClase = new MiClaseParaBBDD(this, "BDUsuarios", null, 1);
        db = miClase.getWritableDatabase();

        listadoAlumnos = (ArrayList<Alumnos>) miClase.getAllAlumnos();
        //Crear instancia del adaptador personalizado
        adaptador = new Adaptador_Personalizado(
                this, R.layout.layout_personalizado_alumnos, listadoAlumnos
        );
        lv_personal.setAdapter(adaptador);





    }
}