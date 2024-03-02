package com.example.repaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll_ciclo;
    private Spinner sp_curso, sp_ciclo;
    private ListView lv_alumnos, lv_vista_personalizada;
    private ArrayList<Alumnos> listaAlumnos;
    private String nombreCurso, nombreCiclo, nombreAlumno;
    private int imagenEso, imagenResto, posicion;
    private EditText et_nombre;

    private SharedPreferences preferencia;

    private Adaptador_Personalizado adaptadorAlumnos;
    private SQLiteDatabase db;
    private Alumnos alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SPINNER Y LINEALYOUT
        sp_curso = findViewById(R.id.sp_curso);
        sp_ciclo = findViewById(R.id.sp_ciclos);
        ll_ciclo = findViewById(R.id.ll_ciclo);
        et_nombre = findViewById(R.id.et_nombre);

        //ListView y arrayAlumnos
        lv_alumnos = findViewById(R.id.lv_alumnos);
        //INICIALIZAMOS LISTA ALUMNSO Y ADAPTADOR ALUMNOS, AMBOS ARRAY DE STRING
        listaAlumnos = new ArrayList<>();

        //Todo zona layout personalizado
        //Crear instancia del adaptador personalizado
        adaptadorAlumnos = new Adaptador_Personalizado(
                this, R.layout.layout_personalizado_alumnos, listaAlumnos
        );
        lv_alumnos.setAdapter(adaptadorAlumnos);


        //todo zona spinner
        configurarSpinnerCurso();
        configurarSpinnerCiclo();



        //TODO ZONA MENU CONTEXTUAL
        registerForContextMenu(lv_alumnos);

        //TODO ZONA BASE DATOS
        //Instanciar objeto de la clase auxiliar.
        MiClaseParaBBDD miClase = new MiClaseParaBBDD(this, "BDUsuarios", null, 1);
        //Invocar el método de apertura de mi BBDD: getReadableDataBase() y getWritableDatabase():
        db = miClase.getWritableDatabase();


        //TODO, ESTO PUEDE FUNCIONA COMO UNA MENU CONTEXTUAL
        /*lv_alumnos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Alumnos alumnos = listaAlumnos.get(position);
                String[] opciones = {"Eliminar", "Mostrar Toast"};
                AlertDialog.Builder ventana = new AlertDialog.Builder(MainActivity.this);

                ventana.setTitle(alumnos.getNombre())
                        .setMessage("¿Esta seguro de eliminar?")
                        .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Alumnos alumno = listaAlumnos.get(position);
                                eliminarAlumno(position);
                                dialog.cancel();//Va hacia atrás
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(MainActivity.this, "Pulsado Cancelar", Toast.LENGTH_SHORT).show();
                                //Todo Toast personalizado
                                Toast toast = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                                toast.setText("CACELADO DESDE NOTIFIACION!AFASDFDF");
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                                toast.show();
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false);//No se cierra la venta hasta que le de al boton

                ventana.show();//Todo, sin el SHOW NO SE VE LA VENTANA EMERGENTE!!!

                return false;
            }
        });*/




    }


    //TODO NOTIFICION
    public void dialogo_ventana_2botones(int position) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);

                ventana.setTitle("AVISO")
                        .setMessage("¿Esta seguro de eliminar?")
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Alumnos alumno = listaAlumnos.get(position);
                        eliminarAlumno(position);
                        dialog.cancel();//Va hacia atrás
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "Pulsado Cancelar", Toast.LENGTH_SHORT).show();
                        //Todo Toast personalizado
                        Toast toast = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
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



    //TODO INFLAMOS EL MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, //Recibe la vista pulsada
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;//Castearlo a un ADAPTER
        posicion = info.position;
        alumnos = (Alumnos) adaptadorAlumnos.getItem(posicion);

        //TODO METERLE EL NOMBRE DEL ALUMNO COMO TITULO
        menu.setHeaderTitle(alumnos.getNombre());

        if(v.getId() == R.id.lv_alumnos){
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

    //TODO METODO PARA ELIMINAR ALUMNO SELECCIONADO
    private void eliminarAlumno(int position) {
        if (position >= 0 && position < listaAlumnos.size()) {

            Alumnos alumnoEliminar = listaAlumnos.get(position);
            listaAlumnos.remove(alumnoEliminar);
            adaptadorAlumnos.notifyDataSetChanged();
        }
    }


    private void configurarSpinnerCurso(){

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.cursos));

        sp_curso.setAdapter(adaptador);

        sp_curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nombreCurso = sp_curso.getSelectedItem().toString();
                if (nombreCurso.equalsIgnoreCase("ciclos")){
                    ll_ciclo.setVisibility(View.VISIBLE);
                }else{
                    ll_ciclo.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //aqui nada

            }
        });

    }

    private void configurarSpinnerCiclo(){

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.ciclos));

        sp_ciclo.setAdapter(adaptador);

        sp_ciclo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nombreCiclo = sp_ciclo.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //aqui nada

            }
        });

    }


    public void onBtnClick(View v) {

        switch (v.getId()){
            case R.id.btn_aceptar:
                guardarAlumnos();
                break;
            case R.id.btn_listarAlumnos:
                listarAlumnos();
                break;
            case R.id.btn_listar_layout_personalizado:
                Intent intent = new Intent(MainActivity.this, Activity3_DatosDesdeBBDD.class);
                startActivity(intent);
                break;

        }//end swtich

    }

    private void listarAlumnos(){
        //todo
        String[] datosRecuperarParametrizado = {"nombre","curso","ciclo"};//Creamos un array de string para datos a recuperar
        Cursor cursor5 = db.query("alumnos", datosRecuperarParametrizado, null, null, null, null, null);
        ArrayList<String> alumnos = new ArrayList<>();
        if(cursor5.moveToFirst()){
            do {
                @SuppressLint("Range") String nombre = cursor5.getString(cursor5.getColumnIndex("nombre"));
                @SuppressLint("Range") String curso = cursor5.getString(cursor5.getColumnIndex("curso"));
                @SuppressLint("Range") String ciclo = cursor5.getString(cursor5.getColumnIndex("ciclo"));

                // Verificamos si el curso es "ESO" o "bach." para decidir cómo agregar la información
                if ("ESO".equalsIgnoreCase(curso) || "bach.".equalsIgnoreCase(curso)) {
                    alumnos.add("Nombre: " + nombre + ", Curso: " + curso);
                } else {
                    alumnos.add("Nombre: " + nombre + ", Curso: " + curso + ", Ciclo: " + ciclo);
                }

            } while (cursor5.moveToNext());

        }else{
            Toast.makeText(this, "Dato inexistente por busqueda parametrizada", Toast.LENGTH_SHORT).show();
        }
        cursor5.close();

        Intent intent = new Intent(MainActivity.this, Activity2_ListarAlumnos.class);
        intent.putStringArrayListExtra("lista_alumnos", alumnos);
        startActivity(intent);

    }

    private void guardarAlumnos() {


        nombreAlumno = et_nombre.getText().toString();

        // TODO Forma de acceder a las imagenes que tengo en el array, en imagenResto parece que da error pero funciona!

        imagenEso = getResources().obtainTypedArray(R.array.imagenes).getResourceId(0, -1);
        imagenResto = getResources().obtainTypedArray(R.array.imagenes).getResourceId(1, -1);


        if(!nombreAlumno.isEmpty()){
            if(nombreCurso.equalsIgnoreCase("eso")){
                Alumnos nuevoAlumno = new Alumnos(nombreAlumno, nombreCurso, imagenEso);
                listaAlumnos.add(nuevoAlumno);

            }else if(nombreCurso.equalsIgnoreCase("bach.")){
                Alumnos nuevoAlumno = new Alumnos(nombreAlumno, nombreCurso, imagenResto);
                listaAlumnos.add(nuevoAlumno);

            }else{
                Alumnos nuevoAlumno = new Alumnos(nombreAlumno, nombreCurso, nombreCiclo, imagenResto);
                listaAlumnos.add(nuevoAlumno);
            }

            // Notificar al adaptador sobre el cambio en la lista
            adaptadorAlumnos.notifyDataSetChanged();
            // Limpiar el EditText después de guardar
            et_nombre.getText().clear();


        } else{
            Toast.makeText(this, "Nombre alumno no puede estar vacio", Toast.LENGTH_SHORT).show();
        }

        //GENERO TOAST
        lv_alumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alumnos alumnoSeleccionado = listaAlumnos.get(position);

                String mensaje = "Alumno: " + alumnoSeleccionado.getNombre() + "\n" +
                        "Curso: " + alumnoSeleccionado.getCurso();
                if (alumnoSeleccionado.getCiclo() != null) {
                    mensaje += "\nCiclo: " + alumnoSeleccionado.getCiclo();
                }

                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        insertarUsuarios();


    }

    private void insertarUsuarios() {



        // Verificar si el código ya existe en la base de datos
        Cursor cursor = db.rawQuery("SELECT nombre, ciclo, curso, imagenEso, imagenResto FROM alumnos WHERE nombre = ?", null);
        if (cursor.getCount() > 0) {
            // El código ya existe, mostrar Toast y no realizar la inserción
            Toast.makeText(this, "El nombre ya existe. Inserción cancelada.", Toast.LENGTH_SHORT).show();
            cursor.close(); // Asegurar cerrar el cursor aquí también para evitar leaks
            return;
        }

        ContentValues registroNuevo = new ContentValues();
        registroNuevo.put("nombre", nombreAlumno);
        registroNuevo.put("curso", nombreCurso);
        // Establece "ciclo" a null o a una cadena vacía si el curso es "ESO" o "bach."
        if ("ESO".equalsIgnoreCase(nombreCurso) || "bach.".equalsIgnoreCase(nombreCurso)) {
            registroNuevo.putNull("ciclo"); // O bien, registroNuevo.put("ciclo", "");

        } else {
            registroNuevo.put("ciclo", nombreCiclo);
        }
        if("ESO".equalsIgnoreCase(nombreCurso)){
            registroNuevo.put("imagenEso", imagenEso);
        }else{
            registroNuevo.put("imagenResto", imagenResto);
        }


        long l = db.insert("alumnos", null, registroNuevo);
        if (l == -1){
            Toast.makeText(this, "Inserción errónea", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Inserción correcta", Toast.LENGTH_SHORT).show();

            et_nombre.setText("");
        }

        cursor.close();
    }

    //TODO CREAR UNA NOTIFICACION QUE PAREZCA UN MENU



}