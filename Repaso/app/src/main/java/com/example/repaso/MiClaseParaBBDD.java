package com.example.repaso;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MiClaseParaBBDD extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios con dos campos
    private final String sqlCreate = "CREATE TABLE alumnos (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, curso TEXT, ciclo TEXT, imagenEso INTEGER, imagenResto INTEGER)";
    private ArrayList<Alumnos> listadoAlumnos;
    private int imagenEso, imagenResto;

    private Context contextoBBDD;//Hace falt crear este context para pasarle al Toast en onCreate!!
    public MiClaseParaBBDD(@Nullable Context context,
                           @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
        this.contextoBBDD = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

        //Aquí también se podrian crear sentencias de inserción de datos iniciales.
        /*try{
            db.execSQL("INSERT into usuarios (codigo, nombre) VALUES (1, 'Pepe Domingo Castaño')");
        }catch (SQLException e){
            Toast.makeText(contextoBBDD, "Error d inserción", Toast.LENGTH_SHORT).show();
        }*/


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Sentencias para actualizacion de la BBDD
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS alumnos");
        //Por ejemplo, se crearía la nueva versión de la tabla
        db.execSQL(sqlCreate);

    }

    @SuppressLint("Range")
    public List<Alumnos> getAllAlumnos() {
        listadoAlumnos = new ArrayList<>();

        // Consulta para obtener todos los datos de la tabla "alumnos"
        String query = "SELECT * FROM alumnos";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        imagenEso = contextoBBDD.getResources().obtainTypedArray(R.array.imagenes).getResourceId(0, -1);
        imagenResto = contextoBBDD.getResources().obtainTypedArray(R.array.imagenes).getResourceId(1, -1);

        if (cursor.moveToFirst()) {
            do {
                Alumnos alumno = new Alumnos();
                alumno.setNombre(cursor.getString(cursor.getColumnIndex("nombre"))); // Ajusta según tus columnas
                alumno.setCurso(cursor.getString(cursor.getColumnIndex("curso")));
                alumno.setCiclo(cursor.getString(cursor.getColumnIndex("ciclo")));

                // Verificar si "curso" es igual a "bach." o si "ciclo" tiene cierto valor
                if ("bach.".equalsIgnoreCase(alumno.getCurso()) || "ciclo".equalsIgnoreCase(alumno.getCiclo())) {
                    alumno.setImagen(imagenResto); // Cambia a la imagen deseada
                } else {
                    alumno.setImagen(imagenEso); // Imagen por defecto o para otro caso
                }

                listadoAlumnos.add(alumno);
            } while (cursor.moveToNext());
        }

        // Cerrar el cursor y la base de datos
        cursor.close();
        db.close();

        return listadoAlumnos;
    }


}
