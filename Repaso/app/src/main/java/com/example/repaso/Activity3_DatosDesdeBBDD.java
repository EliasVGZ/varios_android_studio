package com.example.repaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity3_DatosDesdeBBDD extends AppCompatActivity {

    private ListView lv_personal;
    private MiClaseParaBBDD miClase;
    private static SQLiteDatabase db;
    ArrayList<Alumnos> listadoAlumnos;
    private Adaptador_Personalizado adaptador;
    private int posicion, i;
    private Alumnos alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3_datos_desde_bbdd);

        lv_personal = findViewById(R.id.lv_personal);
        miClase = new MiClaseParaBBDD(this, "BDUsuarios", null, 1);
        db = miClase.getWritableDatabase();

        listadoAlumnos = (ArrayList<Alumnos>) miClase.getAllAlumnos();


        //TODO USO METODO PARA RELLENAR EL LAYOUT PERSONALIZADO
        setupListView(lv_personal, listadoAlumnos);

        registerForContextMenu(lv_personal);

        lv_personal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Seleccion = parent.getItemAtPosition(position).toString();
                Alumnos alumno = listadoAlumnos.get(position);
                String nombreAlumno = alumno.getNombre();

                Toast.makeText(Activity3_DatosDesdeBBDD.this, nombreAlumno, Toast.LENGTH_SHORT).show();
            }
        });



    }

    // Método para configurar la ListView con el adaptador personalizado
    public void setupListView(ListView listView, ArrayList<Alumnos> dataList) {
        adaptador = new Adaptador_Personalizado(
                this, R.layout.layout_personalizado_alumnos, dataList
        );
        listView.setAdapter(adaptador);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, //Recibe la vista pulsada
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;//Castearlo a un ADAPTER
        posicion = info.position;
        alumnos = (Alumnos) adaptador.getItem(posicion);

        //TODO METERLE EL NOMBRE DEL ALUMNO COMO TITULO
        menu.setHeaderTitle(alumnos.getNombre());

        if(v.getId() == R.id.lv_personal){
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
                dialogo_ventana_2botones(info.position);

                return true;


        }
        return super.onContextItemSelected(item);

    }

    public void dialogo_ventana_2botones(int position) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);

        ventana.setTitle("AVISO")
                .setMessage("¿Esta seguro de eliminar?")
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Alumnos alumno = listadoAlumnos.get(position);
                        String nombreAlumno = alumno.getNombre();
                        eliminarAlumnoBBDD(nombreAlumno);
                        eliminarAlumnoListView(position);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "Pulsado Cancelar", Toast.LENGTH_SHORT).show();
                        //Todo Toast personalizado
                        Toast toast = Toast.makeText(Activity3_DatosDesdeBBDD.this, "", Toast.LENGTH_SHORT);
                        toast.setText("Cancelar, TOAST PERSONALIZADO");
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                        toast.show();
                        dialog.cancel();
                    }
                })
                .setTitle("Atención")//Título
                .setCancelable(false);//No se cierra la venta hasta que le de al boton

        ventana.show();//Todo, sin el SHOW NO SE VE LA VENTANA EMERGENTE!!!
    }

    //TODO METODO PARA ELIMINAR ALUMNO SELECCIONADO
    private void eliminarAlumnoListView(int position) {
        if (position >= 0 && position < listadoAlumnos.size()) {

            Alumnos alumnoEliminar = listadoAlumnos.get(position);
            listadoAlumnos.remove(alumnoEliminar);
            adaptador.notifyDataSetChanged();
        }
    }

    private void eliminarAlumnoBBDD(String nombre) {
        if (!db.isOpen()) {
            db = miClase.getWritableDatabase();
        }

        try {
            int i = db.delete("alumnos", "nombre = ?", new String[]{nombre});
            if (i == 0) {
                Log.d("EliminarAlumnoBBDD", "Eliminación parametrizada con errores");
                Toast.makeText(this, "Eliminación parametrizada con errores", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("EliminarAlumnoBBDD", "Eliminado correctamente");
                Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.d("EliminarAlumnoBBDD", "Error al eliminar: " + e.getMessage());
            Toast.makeText(this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}