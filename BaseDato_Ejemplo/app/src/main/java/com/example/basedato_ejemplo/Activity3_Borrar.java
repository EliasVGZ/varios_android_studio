package com.example.basedato_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3_Borrar extends AppCompatActivity {
    private EditText et_codigo;
    private Button btn_ok, btn_fin;
    private static SQLiteDatabase db;
    private MiClaseParaBBDD miClase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3_borrar);

        //Instanciar objeto de la clase auxiliar.
        miClase = new MiClaseParaBBDD(this, "BDUsuarios", null, 1);
        //Invocar el método de apertura de mi BBDD: getReadableDataBase() y getWritableDatabase():
        db = miClase.getWritableDatabase();

        et_codigo = findViewById(R.id.et_codigo);
        btn_fin = findViewById(R.id.btn_fin);
        btn_ok = findViewById(R.id.btn_ok);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                eliminarUsuario();
                break;
            case R.id.btn_fin:
                finish();
                break;

        }
    }

    private void eliminarUsuario() {
        String codigoIngresado = et_codigo.getText().toString();
        int codigo = Integer.parseInt(codigoIngresado);

        int i = db.delete("usuarios", "codigo = "+codigo, null);
        if (i == 0){
            Toast.makeText(this, "Eliminacion parametrizada con errores", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            et_codigo.setText("");
        }


    }
}