package com.example.repaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class MainActivity extends Auxiliar_Activity {

    private LinearLayout ll_ciclo;
    private Spinner sp_curso, sp_ciclo;
    private ListView lv_alumnos, lv_vista_personalizada;
    private ArrayList<Alumnos> listaAlumnos;
    private String nombreCurso, nombreCiclo, nombreAlumno;
    private int imagenEso, imagenResto, posicion;
    private EditText et_nombre;
    private Adaptador_Personalizado adaptadorAlumnos;
    private SQLiteDatabase db;
    private Alumnos alumnos;
    private static final int NOTIFICACION_1 = 1;



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

        //TODO ZONA BASE DATOS
        //Instanciar objeto de la clase auxiliar.
        MiClaseParaBBDD miClase = new MiClaseParaBBDD(this, "BDUsuarios", null, 1);
        //Invocar el método de apertura de mi BBDD: getReadableDataBase() y getWritableDatabase():
        db = miClase.getWritableDatabase();

        //listaAlumnos = (ArrayList<Alumnos>) miClase.getAllAlumnos();


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

        setupListView(lv_alumnos, listaAlumnos);

    }

    // Método para configurar la ListView con el adaptador personalizado
    public void setupListView(ListView listView, ArrayList<Alumnos> dataList) {
        adaptadorAlumnos = new Adaptador_Personalizado(
                this, R.layout.layout_personalizado_alumnos, dataList
        );
        listView.setAdapter(adaptadorAlumnos);
        adaptadorAlumnos.notifyDataSetChanged();
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
                .setCancelable(true);//No se cierra la venta hasta que le de al boton

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

            case R.id.opc_modificar:
                Toast.makeText(this, "Seleccion MODIFICAR!", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.opc_notificacion:
                notificacion_barra_estado(info.position);
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

        // Obteniendo las imágenes
        imagenEso = getResources().obtainTypedArray(R.array.imagenes).getResourceId(0, -1);
        imagenResto = getResources().obtainTypedArray(R.array.imagenes).getResourceId(1, -1);

        if (!nombreAlumno.isEmpty()) {
            Alumnos nuevoAlumno;

            // Crear instancia de Alumnos según el tipo de curso
            if ("eso".equalsIgnoreCase(nombreCurso)) {
                nuevoAlumno = new Alumnos(nombreAlumno, nombreCurso, imagenEso);
            } else if ("bach.".equalsIgnoreCase(nombreCurso)) {
                nuevoAlumno = new Alumnos(nombreAlumno, nombreCurso, imagenResto);
            } else {
                nuevoAlumno = new Alumnos(nombreAlumno, nombreCurso, nombreCiclo, imagenResto);
            }

            // Añadir el nuevo alumno a la lista
            listaAlumnos.add(nuevoAlumno);

            // Notificar al adaptador sobre el cambio en la lista
            adaptadorAlumnos.notifyDataSetChanged();

            // Limpiar el EditText después de guardar
            et_nombre.getText().clear();

            // Mostrar un Toast con la información del alumno
            mostrarToastAlumno(nuevoAlumno);

            // Insertar el nuevo alumno en la base de datos
            insertarUsuarioEnBD();

        } else {
            Toast.makeText(this, "Nombre de alumno no puede estar vacío", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarToastAlumno(Alumnos alumno) {
        // Mostrar un Toast con la información del alumno
        String mensaje = "Alumno: " + alumno.getNombre() + "\n" +
                "Curso: " + alumno.getCurso();
        if (alumno.getCiclo() != null) {
            mensaje += "\nCiclo: " + alumno.getCiclo();
        }

        Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void insertarUsuarioEnBD() {
        try {
            ContentValues registroNuevo = new ContentValues();
            registroNuevo.put("nombre", nombreAlumno);
            registroNuevo.put("curso", nombreCurso);

            if ("ESO".equalsIgnoreCase(nombreCurso) || "bach.".equalsIgnoreCase(nombreCurso)) {
                registroNuevo.putNull("ciclo");
            } else {
                registroNuevo.put("ciclo", nombreCiclo);
            }

            if ("ESO".equalsIgnoreCase(nombreCurso)) {
                registroNuevo.put("imagenEso", imagenEso);
            } else {
                registroNuevo.put("imagenResto", imagenResto);
            }

            long l = db.insert("alumnos", null, registroNuevo);

            if (l == -1) {
                Toast.makeText(this, "Inserción errónea", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Inserción correcta", Toast.LENGTH_SHORT).show();
                et_nombre.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //cursor.close();
    }


    private void notificacion_barra_estado(int position) {

        String cursoA="";
        String nombreA = "";
        String cicloA="";
        Alumnos infoAlumno = null;

        // 1-Crear Notificacion!
        Notification.Builder notificacion = new Notification.Builder(this);
        // 2-Personalizar notificacion!
        //Barra de estado
        notificacion.setSmallIcon(android.R.drawable.ic_delete); //Setear icono pequeño, aparece en la barra de estado
        //Bandeja del sistema
        //Todo Para poner el icono grande en la bandeja del sistema, tenemos que convertir el drawable en bitmap
        if (position >= 0 && position < listaAlumnos.size()) {

            infoAlumno = listaAlumnos.get(position);
            nombreA = infoAlumno.getNombre();
            cursoA = infoAlumno.getCurso();
            cicloA = infoAlumno.getCiclo();

            if(cursoA.equalsIgnoreCase("eso")){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icono_eso);
                notificacion.setLargeIcon(bitmap);
            }else{
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icono_resto);
                notificacion.setLargeIcon(bitmap);
            }
        }

        notificacion.setContentTitle("Mensaje Nuevo!!"); //Título
        notificacion.setContentText("Datos del alumno");//Mensaje
        notificacion.setAutoCancel(true);//Deja de aparecer el icono en la barra de estado al clickear la notificacion
        String mensaje = "Alumno: " + nombreA + "\n" +
                "Curso: " + cursoA;
        if (infoAlumno.getCiclo() != null) {
            mensaje += "\nCiclo: " + cicloA;
        }
        //Incluir texto largo
        notificacion.setStyle(new Notification.BigTextStyle() //Al poner .setStyle machaca el .setContentText y solo se ve el .setStyle
                .bigText(mensaje)
        );



        //Todo Creo un ejemplo de como llamar a otra app
        // Especifica la acción que deseas realizar al hacer clic en la notificación
        // En este ejemplo, se inicia la actividad principal de la aplicación de destino
//        intent.setAction(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        intent.setClassName("com.example.cambiodemoneda", "com.example.cambiodemoneda.MainActivity");

        // 3-Asociar una acción a la pulsacion del usuario
        Intent i = new Intent(this, Activity3_DatosDesdeBBDD.class); //Todo, Ejemplo de como llamar a otra ACTIVITY

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_IMMUTABLE);
        //Asociar el pendingIntent con la notificación!
        notificacion.setContentIntent(pendingIntent);


        // 4-Lanzar la notificacion
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //El notification.builder nos ayudo para construir, ahora tenemos que volver a pasarlo a Notification PARA QUE SEA UNA NOTIFICaCION
        Notification notifi = notificacion.build();//
        notificationManager.notify( NOTIFICACION_1, notifi);// El NOTIFICATION_1 es una constante que añadí arriba

    }




}