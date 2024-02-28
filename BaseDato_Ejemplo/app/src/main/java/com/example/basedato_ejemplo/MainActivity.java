package com.example.basedato_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

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
                // Crear un Intent para pasar de MainActivity a SegundaActivity
                Intent intent = new Intent(MainActivity.this, Activity2_Insertar.class);

                // Opcional: Puedes enviar datos adicionales a la SegundaActivity
                // intent.putExtra("clave", "valor");

                // Iniciar la nueva actividad
                startActivity(intent);

//                //Código directo, sin que el usuario inserte
//                try{
//                    db.execSQL("INSERT into usuarios (codigo, nombre) VALUES (2, 'Nicol Robin')");
//                }catch (SQLException e){
//                    Toast.makeText(this, "No se puedo insertar usuario", Toast.LENGTH_SHORT).show();
//                }
//                //Codigo específico
//                ContentValues registroNuevo = new ContentValues();
//                registroNuevo.put("codigo", 3);//insertar codigo
//                registroNuevo.put("nombre", "Maria Gómez");//insertar nombre
//                long l = db.insert("usuarios", null, registroNuevo);
//                if (l == -1){
//                    Toast.makeText(this, "Insercion erronea", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(this, "Insercion correctamente", Toast.LENGTH_SHORT).show();
//                }

                break;

            case R.id.btn_borrar:

                // Crear un Intent para pasar de MainActivity a SegundaActivity
                intent = new Intent(MainActivity.this, Activity3_Borrar.class);

                // Opcional: Puedes enviar datos adicionales a la SegundaActivity
                // intent.putExtra("clave", "valor");

                // Iniciar la nueva actividad
                startActivity(intent);

                //Código directo, sin que el usuario inserte
//                try{
//                    db.execSQL("delete from usuarios where codigo = 3");
//                }catch (SQLException e){
//                    Toast.makeText(this, "BBBBBBBBorrado directa erronea", Toast.LENGTH_SHORT).show();
//                }
//                //Codigo específico parametrizado
//
//                int i = db.delete("usuarios", "codigo = 2", null);
//                if (i == 0){
//                    Toast.makeText(this, "Eliminacion parametrizada con errores", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
//                }



                break;
            case R.id.btn_modificar:

                // Crear un Intent para pasar de MainActivity a SegundaActivity
                intent = new Intent(MainActivity.this, Activity4_Modificar.class);

                // Opcional: Puedes enviar datos adicionales a la SegundaActivity
                // intent.putExtra("clave", "valor");

                // Iniciar la nueva actividad
                startActivity(intent);
//                //Código directo, sin que el usuario inserte
//                try{
//                    db.execSQL("update usuarios  set nombre='Elias' where codigo = 1");
//                }catch (SQLException e){
//                    Toast.makeText(this, "Modificadoooo directa erronea", Toast.LENGTH_SHORT).show();
//                }
//
//                //Codigo específico
//                ContentValues registroModificar = new ContentValues();
//
//                registroModificar.put("nombre", "ValorrrrrrrrA");//insertar codigo
//
//                int j = db.update("usuarios", registroModificar, "codigo=1", null);
//                if (j == 0){
//                    Toast.makeText(this, "Modificacion parametrizada con errores", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(this, "Modificacion correctamente", Toast.LENGTH_SHORT).show();
//                }


                break;
            case R.id.btn_mostrar_uno:
                 // todo Codigo chatgpt
                // Crear un Intent para pasar de MainActivity a SegundaActivity
                intent = new Intent(MainActivity.this, Activity5_ListarUsuarios.class);
                // Iniciar la nueva actividad
                startActivity(intent);

                //TODO EXPLICACION LULY:
                /*//------------Codigo SQL directo---------------
                Cursor cursor = db.rawQuery("SELECT nombre FROM usuarios WHERE codigo = 1", null);
                if (cursor.moveToFirst()){//Si me devuelve
                    String nombre = cursor.getString(0);
                    Toast.makeText(this, "Nombre: "+nombre, Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this, "Dato inexistente", Toast.LENGTH_SHORT).show();
                }
                cursor.close();//Importante cerrar el cursor

                //--------------Método parametrizado-------------//
                String[] datosRecuperar = {"nombre"};//Creamos un array de string para datos a recuperar
                Cursor cursor2 = db.query("usuarios", datosRecuperar, "codigo = 2", null, null, null, null);
                if(cursor2.moveToFirst()){
                    String nombre = cursor2.getString(0);
                    Toast.makeText(this, "Nombre: "+nombre, Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this, "Dato inexistente por busqueda parametrizada", Toast.LENGTH_SHORT).show();
                }
                cursor2.close();*/

                break;

            case R.id.btn_mostrar_todos:

                //todo Codigo CHATGPT
                Cursor cursor3 = db.rawQuery("SELECT codigo, nombre FROM usuarios", null);
                ArrayList<String> usuarios = new ArrayList<>();

                while (cursor3.moveToNext()) {
                    @SuppressLint("Range") int codigo = cursor3.getInt(cursor3.getColumnIndex("codigo"));
                    @SuppressLint("Range") String nombre = cursor3.getString(cursor3.getColumnIndex("nombre"));
                    usuarios.add("Código: " + codigo + ", Nombre: " + nombre);
                }
                cursor3.close();

                intent = new Intent(MainActivity.this, Activity6_ListarTodos.class);
                intent.putStringArrayListExtra("lista_usuarios", usuarios);
                startActivity(intent);

                //TODO Codigo Luly:
                //------------Codigo SQL directo---------------//
                /*Cursor cursor4 = db.rawQuery("SELECT * FROM usuarios", null);
                if (cursor4.moveToFirst()){//Si me devuelve algun rsultado
                    do{
                        int codigo = cursor4.getInt(0); //Campo codigo recuperado está en la posicion 0
                        String nombre = cursor4.getString(1);//Campo nombre recuperado está en la posicion 1

                        Toast.makeText(this, "Nombre: "+nombre+ "\n "+"CVodigo: "+codigo, Toast.LENGTH_SHORT).show();
                    }while(cursor4.moveToNext());
                }else{
                    Toast.makeText(this, "Dato inexistente", Toast.LENGTH_SHORT).show();
                }
                cursor4.close();//Importante cerrar el cursor*/


                //--------------Método parametrizado-------------//
                /*String[] datosRecuperarParametrizado = {"codigo","nombre"};//Creamos un array de string para datos a recuperar
                Cursor cursor5 = db.query("usuarios", datosRecuperarParametrizado, null, null, null, null, null);
                if(cursor5.moveToFirst()){
                    do{
                        int codigo = cursor5.getInt(0); //Campo codigo recuperado está en la posicion 0
                        String nombre = cursor5.getString(1);//Campo nombre recuperado está en la posicion 1

                        Toast.makeText(this, "Nombreeeee: "+nombre+ "\n "+"Codigooooo: "+codigo, Toast.LENGTH_SHORT).show();
                    }while(cursor5.moveToNext());

                }else{
                    Toast.makeText(this, "Dato inexistente por busqueda parametrizada", Toast.LENGTH_SHORT).show();
                }
                cursor5.close();*/

                break;


        }
    }


}