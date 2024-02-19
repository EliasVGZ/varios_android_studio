package com.example.notificaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_mensaje, btn_1boton, btn_2botones, btn_3botones;

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
    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.btn_lista_simple:
                dialogo_lista_simple();
                break;
            case R.id.btn_lista_simple_aceptar_opcion:
                dialogo_lista_simple_aceptar();
                break;

        }
    }

    private void dialogo_lista_simple_aceptar() {
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
                .setTitle("Atención")//Título
                .setCancelable(false);//No se cierra la venta hasta que le de al boton

        ventana.show();//Todo, sin el SHOW NO SE VE LA VENTANA EMERGENTE!!!
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


}

