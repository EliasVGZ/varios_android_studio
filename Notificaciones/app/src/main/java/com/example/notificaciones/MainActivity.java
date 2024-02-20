package com.example.notificaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_mensaje, btn_1boton, btn_2botones, btn_3botones;
    private String seleccion;
    // Inicializar el StringBuilder para almacenar selecciones
    final StringBuilder selecciones = new StringBuilder();
    private String[] sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_mensaje = findViewById(R.id.btn_mensaje);
        btn_1boton = findViewById(R.id.btn_1boton);
        btn_2botones = findViewById(R.id.btn_2botones);
        btn_3botones = findViewById(R.id.btn_3botones);

        btn_mensaje.setOnClickListener(this);
        btn_1boton.setOnClickListener(this);
        btn_2botones.setOnClickListener(this);
        btn_3botones.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mensaje:
                dialogoMensaje();
                break;
            case R.id.btn_1boton:
                dialogo_ventana_1boton();
                break;
            case R.id.btn_2botones:
                dialogo_ventana_2botones();
                break;
            case R.id.btn_3botones:
                dialogo_ventana_3botones();
                break;
        }

    }

    //Todo onclickBtn desde XML, LO CREA PARA VER SI FUNCIONA!!!!!!!
    @SuppressLint("NonConstantResourceId")
    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.btn_lista_simple:
                dialogo_lista_simple();
                break;
            case R.id.btn_lista_simple_aceptar_opcion://TODO lista con SETSINGLECHOICEITEMS
                dialogo_single_opcion();
                break;
            case R.id.btn_lista_multiple_opciones:
                dialogo_multiple_opciones();
                break;

        }
    }

    /**
     * Método para crear un MultipleChoice -> Elegir varias oppciones por medio de los checkbox!
     */
    private void dialogo_multiple_opciones() {

        boolean[] coloresSeleccionados = {false, false, false, false};

        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setIcon(R.drawable.ic_launcher_background)
                .setTitle("Elige colores")
                .setMultiChoiceItems(R.array.colores, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked){
                            coloresSeleccionados[which] = true;
                        }else{
                            coloresSeleccionados[which] = false;
                        }
                    }
                });
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Recorrer el array booleano con la indicacion de los colores seleccionados
                String strSeleccionados = " ";
                for (int i = 0; i < coloresSeleccionados.length; i++) {
                    if(coloresSeleccionados[i]){//Si es true, lo añadimos al string
                        strSeleccionados += getResources().getStringArray(R.array.colores)[i] + "\n";
                    }
                }
                Toast.makeText(MainActivity.this, strSeleccionados, Toast.LENGTH_SHORT).show();
            }
        });
        ventana.show();
    }

    /**
     * Metodo para CREAR UN SingleChoiceItems -> elegir una de la oopciones y al darle a aceptar va a una pagina web!
     */
    private void dialogo_single_opcion() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setIcon(R.drawable.img_planeta)
                .setTitle("Elige color")
                .setSingleChoiceItems(R.array.colores, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        seleccion = getResources().getStringArray(R.array.colores)[which];
                        //Toast.makeText(MainActivity.this, "Has seleccionado: " + seleccion, Toast.LENGTH_SHORT).show();
                    }
                });
        ventana.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Has seleccionado el color: "+seleccion, Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(MainActivity.this, "No se puede abrir el navegador web", Toast.LENGTH_SHORT).show();
//                }
                abrirWikipediaPorColor(seleccion);
            }
        });

        ventana.show();
    }


    private void dialogo_lista_simple() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setIcon(R.drawable.img_planeta)
                .setTitle("Elige color")
                .setItems(R.array.colores, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String seleccion = getResources().getStringArray(R.array.colores)[which];//Todo, capturamos la seleccion del usuario
                        Toast.makeText(MainActivity.this, "Has seleccionado: "+seleccion, Toast.LENGTH_SHORT).show();
                    }
                });


        ventana.show();
    }


    private void dialogo_ventana_3botones() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setIcon(R.drawable.img_planeta)
                .setMessage("Es una vennttana emergente con 1 boton")
                .setPositiveButton(R.string.boton_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo Operaciones correspondientes
                        Toast.makeText(MainActivity.this, "Pulsado Ok", Toast.LENGTH_SHORT).show();
                        dialog.cancel();//Va hacia atrás
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Pulsado Cancelar", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Pulsado neutral", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setTitle("Atención")//Título
                .setCancelable(false);//No se cierra la venta hasta que le de al boton

        ventana.show();//Todo, sin el SHOW NO SE VE LA VENTANA EMERGENTE!!!
    }

    private void dialogo_ventana_2botones() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setIcon(R.drawable.img_planeta)
                .setMessage("Es una vennttana emergente con 1 boton")
                .setPositiveButton(R.string.boton_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo Operaciones correspondientes
                        toastPersonalizada();
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

    private void toastPersonalizada() {
        LinearLayout ll_toast = findViewById(R.id.ll_toast);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toast_personalizada, null);
        TextView tv_mensaje_toast = view.findViewById(R.id.tv_mensaje_toast);
        tv_mensaje_toast.setText("Pulsaste OK, ToastPersonalizada");

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);//Asociamos la vista inflada con el toast
        toast.show();
    }

    private void dialogo_ventana_1boton() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setIcon(R.drawable.img_planeta)
                .setMessage("Es una vennttana emergente con 1 boton")
                .setPositiveButton(R.string.boton_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo Operaciones correspondientes
                        dialog.cancel();//Va hacia atrás
                    }
                })
                .setTitle("Atención")//Título
                .setCancelable(false);//No se cierra la venta hasta que le de al boton

        ventana.show();//Todo, sin el SHOW NO SE VE LA VENTANA EMERGENTE!!!
    }

    private void dialogoMensaje() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setIcon(R.drawable.img_planeta);
        ventana.setMessage("Es una vennttana emergente");
        ventana.setTitle("Atención");

        ventana.show();

    }

    /**
     * Método para que al seleccionar un item en SingleChoice escuche esa selección y vaya a wikipedia
     *
     */

    private void abrirWikipediaPorColor(String color) {
        // Mapear los colores a las URLs de Wikipedia correspondientes
        String urlWikipedia;
        switch (color.toLowerCase()) {
            case "rojo":
                urlWikipedia = "https://es.wikipedia.org/wiki/Rojo";
                break;
            case "verde":
                urlWikipedia = "https://es.wikipedia.org/wiki/Verde";
                break;
            case "azul":
                urlWikipedia = "https://es.wikipedia.org/wiki/Azul";
                break;
            case "negro":
                urlWikipedia = "https://es.wikipedia.org/wiki/Negro";
                break;
            default:
                // Si el color no coincide con ninguno de los casos, puedes manejarlo según tus necesidades
                urlWikipedia = "https://es.wikipedia.org/wiki/Color";
                break;
        }

        // Crear un Intent para abrir la página de Wikipedia en un navegador web
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlWikipedia));

        // Verificar si hay aplicaciones que puedan manejar este Intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Iniciar la actividad del navegador web
            startActivity(intent);
        } else {
            // Manejar la situación donde no hay aplicaciones que puedan manejar el Intent
            Toast.makeText(MainActivity.this, "No se puede abrir el navegador web", Toast.LENGTH_SHORT).show();
        }
    }


}

