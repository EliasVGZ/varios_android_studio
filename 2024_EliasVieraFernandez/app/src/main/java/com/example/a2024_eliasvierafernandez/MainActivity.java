package com.example.a2024_eliasvierafernandez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Auxiliar_Activity {

    LinearLayout ll_principal, ll_quimico;
    ImageButton ib_imagenGrande;
    ListView lv_quimicos;
    private ArrayList<Quimicos> listaQuimicos;
    private Adaptador_Personalizado adaptadorQuimicos;
    private SQLiteDatabase db;
    private Quimicos quimicos;
    private static final int NOTIFICACION_1 = 1;
    String seleccion;
    int posicion;

    private MiClaseParaBBDD miClase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_principal = findViewById(R.id.ll_principal);
        ll_quimico = findViewById(R.id.ll_quimico);
        ib_imagenGrande = findViewById(R.id.ib_imagenGrande);
        lv_quimicos = findViewById(R.id.lv_quimicos);


        miClase = new MiClaseParaBBDD(this, "BDQuimicos", null, 1);
        //Invocar el método de apertura de mi BBDD: getReadableDataBase() y getWritableDatabase():
        db = miClase.getWritableDatabase();

        //listaQuimicos = (ArrayList<Quimicos>) miClase.getAllQuimicos();
        listaQuimicos = (ArrayList<Quimicos>) miClase.getAllQuimicos();
        adaptadorQuimicos = new Adaptador_Personalizado(
                this, R.layout.layout_personalizado, listaQuimicos
        );
        lv_quimicos.setAdapter(adaptadorQuimicos);


        //Si la cargo aqui no me aparece el nuevo quimico guardado!
        //setupListView(lv_quimicos, listaQuimicos);
        registerForContextMenu(lv_quimicos);

        lv_quimicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccion = listaQuimicos.get(position).getCompuesto();
                Toast.makeText(MainActivity.this, seleccion, Toast.LENGTH_SHORT).show();
                Intent myintent = new Intent(MainActivity.this, Activity2.class);
                //añadir dato al input, le damos un nombre al dato y le mandamos el dato
                myintent.putExtra("mensaje", seleccion);

                startActivity(myintent);

            }
        });


    }
   //@Override
    /*protected void onResume() {
        super.onResume();
        // Actualiza la lista cada vez que la actividad vuelve a estar en primer plano
        setupListView(lv_quimicos, listaQuimicos);
    }


    public void setupListView(ListView listView, ArrayList<Quimicos> dataList) {
        adaptadorQuimicos = new Adaptador_Personalizado(
                this, R.layout.layout_personalizado, dataList
        );
        listView.setAdapter(adaptadorQuimicos);
        adaptadorQuimicos.notifyDataSetChanged();
    }*/

    //TODO INFLAMOS EL MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, //Recibe la vista pulsada
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;//Castearlo a un ADAPTER
        posicion = info.position;
        quimicos = (Quimicos) adaptadorQuimicos.getItem(posicion);

        //TODO METERLE EL NOMBRE DEL ALUMNO COMO TITULO
        menu.setHeaderTitle(quimicos.getCompuesto());

        if(v.getId() == R.id.lv_quimicos){
            inflater.inflate(R.menu.menu_eliminar, menu);//Menu asociado a la etiqueta

        }

    }

    //Listener para el menu contextual
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch (item.getItemId()){
            case R.id.opc_eliminar:
                dialogo_ventana_2botones(info.position);




            case R.id.opc_obras:
                Toast.makeText(this, "en obras", Toast.LENGTH_SHORT).show();

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
                        Quimicos quimicos = listaQuimicos.get(position);

                        String nombreAQuimico = quimicos.getCompuesto();
                        eliminarQuimicosBBDD(nombreAQuimico);
                        eliminarAlumno(position);

                        dialog.cancel();//Va hacia atrás
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                })
                .setTitle("Atención")//Título
                .setCancelable(true);//No se cierra la venta hasta que le de al boton

        ventana.show();//Todo, sin el SHOW NO SE VE LA VENTANA EMERGENTE!!!
    }

    private void eliminarAlumno(int position) {
        if (position >= 0 && position < listaQuimicos.size()) {

            Quimicos alumnoEliminar = listaQuimicos.get(position);

            listaQuimicos.remove(alumnoEliminar);
            adaptadorQuimicos.notifyDataSetChanged();
        }
    }

    private void eliminarQuimicosBBDD(String nombre) {
        if (!db.isOpen()) {
            db = miClase.getWritableDatabase();
        }

        try {
            int i = db.delete("quimicos", "compuesto = ?", new String[]{nombre});
            if (i == 0) {
                Toast.makeText(this, "Eliminación parametrizada con errores", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    // Método para configurar la ListView con el adaptador personalizado


    public void onBtnClick(View v) {

        switch (v.getId()){
            case R.id.ib_imagenGrande:

                ll_principal.setVisibility(View.GONE);
                ll_quimico.setVisibility(View.VISIBLE);

                break;


        }//end swtich

    }


}