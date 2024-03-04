package com.example.basedato_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity4_Modificar extends AppCompatActivity {

    private EditText et_codigo, et_nombre;
    private Button btn_ok, btn_fin;
    private static SQLiteDatabase db;
    private MiClaseParaBBDD miClase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity4_modificar);

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
                modificarUsuario();
                break;
            case R.id.btn_fin:
                finish();
                break;

        }
    }

    private void modificarUsuario() {

        String codigoIngresado = et_codigo.getText().toString();
        int codigo = Integer.parseInt(codigoIngresado);
        String nombreUsuario = et_nombre.getText().toString();

        ContentValues registroModificar = new ContentValues();

        registroModificar.put("nombre", nombreUsuario);//insertar codigo

        int j = db.update("usuarios", registroModificar, "codigo = "+codigo, null);
        if (j == 0){
            Toast.makeText(this, "Modificacion parametrizada con errores", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Modificacion correctamente", Toast.LENGTH_SHORT).show();
            et_codigo.setText("");
            et_nombre.setText("");
                }


    }
}