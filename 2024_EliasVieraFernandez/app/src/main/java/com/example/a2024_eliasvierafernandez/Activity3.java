package com.example.a2024_eliasvierafernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity implements View.OnClickListener{

    private EditText nombre, formula;
    private Button btn_insertar;
    private SQLiteDatabase db;
    private MiClaseParaBBDD miClase;
    private ArrayList<Quimicos> listaQuimicos;

    private Adaptador_Personalizado adaptador;
    String nombreCom;
    String formulaFor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        //Instanciar objeto de la clase auxiliar.
        miClase = new MiClaseParaBBDD(this, "BDQuimicos", null, 1);
        //Invocar el método de apertura de mi BBDD: getReadableDataBase() y getWritableDatabase():
        db = miClase.getWritableDatabase();
        btn_insertar = findViewById(R.id.btn_insertar);
        btn_insertar.setOnClickListener(this);
        listaQuimicos = new ArrayList<>();

        adaptador = new Adaptador_Personalizado(this,
                R.layout.layout_personalizado,
                listaQuimicos
        );

        nombre = findViewById(R.id.nombre);
        formula = findViewById(R.id.formula);
        btn_insertar = findViewById(R.id.btn_insertar);
    }






    private void guardarQuimico() {
        nombreCom = nombre.getText().toString();
        formulaFor = formula.getText().toString();


        if (!nombreCom.isEmpty() && !formulaFor.isEmpty()) {
            Quimicos nuevo;

            nuevo = new Quimicos(nombreCom, formulaFor);
            listaQuimicos.add(nuevo);
            adaptador.notifyDataSetChanged();



            insertarQuimicosEnBD();




        } else {
            Toast.makeText(this, "NO PUEDE ESTAR VACIO", Toast.LENGTH_SHORT).show();
        }
    }



    private void insertarQuimicosEnBD() {
        try {
            ContentValues registroNuevo = new ContentValues();
            registroNuevo.put("compuesto", nombreCom);
            registroNuevo.put("formula", formulaFor);


            long l = db.insert("quimicos", null, registroNuevo);

            if (l == -1) {
                Toast.makeText(this, "Inserción errónea", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Inserción correcta", Toast.LENGTH_SHORT).show();
                nombre.setText("");
                formula.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //cursor.close();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insertar:
                guardarQuimico();
                break;

        }

    }
    //TODO PARA QUE AL DARLE PARA ATRAS SE GUARDEN LOS DATOS
    @Override
    protected void onResume() {
        super.onResume();
        guardarQuimico();


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CICLO", "ejecutando ON STOP MAINACTIVITY");
        guardarQuimico();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CICLO", "ejecutando ONDESTROY EN MAINACTIVITY");
        // Recargar datos al reanudar la actividad
        guardarQuimico();

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CICLO", "ejecutando ONRESTAR EN MAINACTIVITY");
        guardarQuimico();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CICLO", "ejecutando ON PAUSE EN MAINACTIVITY");
        guardarQuimico();
    }


}