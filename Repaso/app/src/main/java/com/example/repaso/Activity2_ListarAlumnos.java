package com.example.repaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2_ListarAlumnos extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    private ArrayList<String> listaAlumnos;
    private ArrayList<Alumnos> listadoAlumnos;
    private int posicion;
    MainActivity main;
    private ListView listView;
    private MiClaseParaBBDD miClase;
    private static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_listar_alumnos);

        listView = findViewById(R.id.listView);
        main = new MainActivity();
        listadoAlumnos = new ArrayList<>();
        listaAlumnos = getIntent().getStringArrayListExtra("lista_alumnos");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAlumnos);

        listView.setAdapter(adapter);
        //TODO ZONA MENU CONTEXTUAL
        registerForContextMenu(listView);
        miClase = new MiClaseParaBBDD(this, "BDUsuarios", null, 1);
        db = miClase.getWritableDatabase();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, //Recibe la vista pulsada
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;//Castearlo a un ADAPTER
        posicion = info.position;
        menu.setHeaderTitle("AVISO");



        if(v.getId() == R.id.listView){
            inflater.inflate(R.menu.menu_alumnos, menu);//Menu asociado a la etiqueta
        }

    }

    //Listener para el menu contextual
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //TODO PARA CONOCER LA POSICION DEL ALUMNO
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch (item.getItemId()){
            case R.id.opc_eliminar:
                eliminarAlumno();

                return true;
            case R.id.opc_modificar:
                Toast.makeText(this, "Has elegido la opcion otros!", Toast.LENGTH_SHORT).show();

        }
        return super.onContextItemSelected(item);

    }

    private void eliminarAlumno() {

        int i = db.delete("alumnos", "_id = "+posicion, null);
        if (i == 0){
            Toast.makeText(this, "Eliminacion parametrizada con errores", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
        }

        listaAlumnos.remove(posicion);
        adapter.notifyDataSetChanged();

    }




}