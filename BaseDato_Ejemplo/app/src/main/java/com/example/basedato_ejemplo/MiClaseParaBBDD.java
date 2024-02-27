package com.example.basedato_ejemplo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MiClaseParaBBDD extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios con dos campos
    private final String sqlCreate = "CREATE TABLE usuarios (codigo INTEGER PRIMARY KEY, nombre TEXT)";
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
        try{
            db.execSQL("INSERT into usuarios (codigo, nombre) VALUES (1, 'Pepe Domingo Castaño')");
        }catch (SQLException e){
            Toast.makeText(contextoBBDD, "Error d inserción", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Sentencias para actualizacion de la BBDD
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        //Por ejemplo, se crearía la nueva versión de la tabla
        db.execSQL(sqlCreate);

    }
}
