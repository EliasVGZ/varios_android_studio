package com.example.basedato_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2_Insertar extends AppCompatActivity {

    private EditText et_codigo, et_nombre;
    private Button btn_ok, btn_fin;
    private  SQLiteDatabase db;
    private MiClaseParaBBDD miClase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_insertar);

        //Instanciar objeto de la clase auxiliar.
        miClase = new MiClaseParaBBDD(this, "BDUsuarios", null, 1);
        //Invocar el método de apertura de mi BBDD: getReadableDataBase() y getWritableDatabase():
        db = miClase.getWritableDatabase();

        et_codigo = findViewById(R.id.et_codigo);
        et_nombre = findViewById(R.id.et_nombre);
        btn_fin = findViewById(R.id.btn_fin);
        btn_ok = findViewById(R.id.btn_ok);

    }

    @SuppressLint("NonConstantResourceId")
    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                insertarUsuarios();
                break;
            case R.id.btn_fin:
                finish();
                break;

        }
    }

    private void insertarUsuarios() {
        String codigoIngresado = et_codigo.getText().toString();
        String nombre = et_nombre.getText().toString();

        // Verificar si el campo de código está vacío o si el nombre está vacío
        if (codigoIngresado.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codigoIngresado);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El código debe ser numérico.", Toast.LENGTH_SHORT).show();
            return;
        }

        String nombreUsuario = et_nombre.getText().toString();

        // Verificar si el código ya existe en la base de datos
        Cursor cursor = db.rawQuery("SELECT codigo FROM usuarios WHERE codigo = ?", new String[]{String.valueOf(codigo)});
        if (cursor.getCount() > 0) {
            // El código ya existe, mostrar Toast y no realizar la inserción
            Toast.makeText(this, "El código ya existe. Inserción cancelada.", Toast.LENGTH_SHORT).show();
            cursor.close(); // Asegurar cerrar el cursor aquí también para evitar leaks
            return;
        }

        ContentValues registroNuevo = new ContentValues();
        registroNuevo.put("codigo", codigo); // insertar código
        registroNuevo.put("nombre", nombreUsuario); // insertar nombre
        long l = db.insert("usuarios", null, registroNuevo);
        if (l == -1){
            Toast.makeText(this, "Inserción errónea", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Inserción correcta", Toast.LENGTH_SHORT).show();
            // Limpiar los EditText después de la inserción
            et_codigo.setText("");
            et_nombre.setText("");
        }

        cursor.close();
    }



}