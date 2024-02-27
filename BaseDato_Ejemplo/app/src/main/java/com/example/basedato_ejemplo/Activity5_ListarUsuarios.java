package com.example.basedato_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity5_ListarUsuarios extends AppCompatActivity {

    private EditText et_codigo;
    private TextView tv_consulta_usuario;
    private Button btn_ok, btn_fin;
    private static SQLiteDatabase db;
    private MiClaseParaBBDD miClase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity5_listar_usuarios);

        //Instanciar objeto de la clase auxiliar.
        miClase = new MiClaseParaBBDD(this, "BDUsuarios", null, 1);
        //Invocar el método de apertura de mi BBDD: getReadableDataBase() y getWritableDatabase():
        db = miClase.getWritableDatabase();

        et_codigo = findViewById(R.id.et_codigo);
        btn_fin = findViewById(R.id.btn_fin);
        btn_ok = findViewById(R.id.btn_ok);
        tv_consulta_usuario = findViewById(R.id.tv_consulta_usuario);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                consultarUsuario();
                break;
            case R.id.btn_fin:
                finish();
                break;

        }
    }

    private void consultarUsuario() {
        String codigoIngresado = et_codigo.getText().toString();
        int codigo = Integer.parseInt(codigoIngresado);

        if(codigoIngresado.isEmpty()){
            Toast.makeText(this, "Debes ingresar un codigo", Toast.LENGTH_SHORT).show();
        }else{
            // Realizar la consulta en la base de datos
            Cursor cursor = db.rawQuery("SELECT nombre FROM usuarios WHERE codigo = ?", new String[]{String.valueOf(codigo)});

            if (cursor.moveToFirst()) {
                // Si hay resultados, obtener el nombre y mostrarlo
                @SuppressLint("Range") String nombreUsuario = cursor.getString(cursor.getColumnIndex("nombre"));
                tv_consulta_usuario.setText("Usuario con codigo "+codigoIngresado+" es : "+nombreUsuario);

                Toast.makeText(this, "Nombre del usuario con código " + codigo + ": " + nombreUsuario, Toast.LENGTH_SHORT).show();
            } else {
                // Si no hay resultados, mostrar un mensaje indicando que no se encontró el usuario
                Toast.makeText(this, "No se encontró un usuario con el código " + codigo, Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        }






    }
}