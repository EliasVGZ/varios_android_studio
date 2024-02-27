package com.example.basedato_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciar objeto de la clase auxiliar.
        MiClaseParaBBDD miClase = new MiClaseParaBBDD(this, "BDUsuarios", null, 1);
        //Invocar el método de apertura de mi BBDD: getReadableDataBase() y getWritableDatabase():
        db = miClase.getWritableDatabase();

    }

    @SuppressLint("NonConstantResourceId")
    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.btn_insertar:
                //Código directo, sin que el usuario inserte
                try{
                    db.execSQL("INSERT into usuarios (codigo, nombre) VALUES (2, 'Nicol Robin')");
                }catch (SQLException e){
                    Toast.makeText(this, "No se puedo insertar usuario", Toast.LENGTH_SHORT).show();
                }
                //Codigo específico
                ContentValues registroNuevo = new ContentValues();
                registroNuevo.put("codigo", 3);//insertar codigo
                registroNuevo.put("nombre", "Maria Gómez");//insertar nombre
                long l = db.insert("usuarios", null, registroNuevo);
                if (l == -1){
                    Toast.makeText(this, "Insercion erronea", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Insercion correctamente", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_borrar:
                //Código directo, sin que el usuario inserte
                try{
                    db.execSQL("delete from usuarios where codigo = 3");
                }catch (SQLException e){
                    Toast.makeText(this, "BBBBBBBBorrado directa erronea", Toast.LENGTH_SHORT).show();
                }
                //Codigo específico parametrizado

                int i = db.delete("usuarios", "codigo = 2", null);
                if (i == 0){
                    Toast.makeText(this, "Eliminacion parametrizada con errores", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                }



                break;
            case R.id.btn_modificar:
                //Código directo, sin que el usuario inserte
                try{
                    db.execSQL("update usuarios  set nombre='Elias' where codigo = 1");
                }catch (SQLException e){
                    Toast.makeText(this, "Modificadoooo directa erronea", Toast.LENGTH_SHORT).show();
                }

                //Codigo específico
                ContentValues registroModificar = new ContentValues();

                registroModificar.put("nombre", "ValorrrrrrrrA");//insertar codigo

                int j = db.update("usuarios", registroModificar, "codigo=1", null);
                if (j == 0){
                    Toast.makeText(this, "Modificacion parametrizada con errores", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Modificacion correctamente", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.btn_mostrar_uno:

                break;
            case R.id.btn_mostrar_todos:

                break;

        }
    }


}