package com.example.a2024_eliasvierafernandez;

import android.annotation.SuppressLint;
import android.content.Context;
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
    private final String sqlCreate = "CREATE TABLE quimicos (compuesto TEXT PRIMARY KEY, formula TEXT)";
    private ArrayList<Quimicos> listadoQuimicos;
    private Context contextoBBDD;//Hace falt crear este context para pasarle al Toast en onCreate!!
    int imagen, imagen2;
    public MiClaseParaBBDD(@Nullable Context context,
                           @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
        this.contextoBBDD = context;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

        imagen = contextoBBDD.getResources().obtainTypedArray(R.drawable.flask1).getResourceId(0, -1);


        //Aquí también se podrian crear sentencias de inserción de datos iniciales.
        try{
            db.execSQL("INSERT into quimicos (compuesto, formula) VALUES ('Acido Sulfúrico', 'SO4H2')");
            db.execSQL("INSERT into quimicos (compuesto, formula) VALUES ('Agua', 'H2O')");
            db.execSQL("INSERT into quimicos (compuesto, formula) VALUES ('Carbonato cálcico', 'CO3CA')");
            db.execSQL("INSERT into quimicos (compuesto, formula) VALUES ('Anhídrico carbónico', 'CO2')");
            db.execSQL("INSERT into quimicos (compuesto, formula) VALUES ('Monóxido de carbono', 'CO')");
        }catch (SQLException e){
            Toast.makeText(contextoBBDD, "Error de inserción", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Sentencias para actualizacion de la BBDD
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS quimicos");
        //Por ejemplo, se crearía la nueva versión de la tabla
        db.execSQL(sqlCreate);

    }

    @SuppressLint({"Range", "ResourceType"})
    public List<Quimicos> getAllQuimicos() {
        listadoQuimicos = new ArrayList<>();

        // Consulta para obtener todos los datos de la tabla "alumnos"
        String query = "SELECT * FROM quimicos";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

            //2 FORMAS DE HACERLO!!
        imagen = R.drawable.flask1;
        imagen2 = contextoBBDD.getResources().obtainTypedArray(R.array.imagenes).getResourceId(0, -1);

        if (cursor.moveToFirst()) {
            do {
                Quimicos quimicos = new Quimicos();
                quimicos.setCompuesto(cursor.getString(cursor.getColumnIndex("compuesto")));
                quimicos.setFormula(cursor.getString(cursor.getColumnIndex("formula")));
                quimicos.setImagen(imagen2);

                listadoQuimicos.add(quimicos);
            } while (cursor.moveToNext());
        }
        // Cerrar el cursor y la base de datos
        cursor.close();
        //db.close();

        return listadoQuimicos;
    }
}
