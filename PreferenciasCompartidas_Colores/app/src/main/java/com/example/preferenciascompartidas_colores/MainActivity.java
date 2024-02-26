package com.example.preferenciascompartidas_colores;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    String seleccion;
    private TextView tv_color;

    private SharedPreferences preferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_color = findViewById(R.id.tv_color);
        preferencia = PreferenceManager.getDefaultSharedPreferences(this); //Instanciamos el SharedPreferences
        cargarColorGuardado();
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.btn_elegir_colores:
                dialogo_single_opcion();
                break;

        }
    }

    private void dialogo_single_opcion() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Elige color")
                .setSingleChoiceItems(R.array.colores, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        seleccion = getResources().getStringArray(R.array.colores)[which];

                    }
                });
        ventana.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                elegirColor(seleccion);

                //Todo, Realizo preferencedshared
                SharedPreferences.Editor editorPreferencia = preferencia.edit();
                editorPreferencia.putString("color", seleccion);
                editorPreferencia.apply();


                Toast.makeText(MainActivity.this, "Has seleccionado el color: "+seleccion, Toast.LENGTH_SHORT).show();

            }
        });

        ventana.show();
    }
    private void cargarColorGuardado() { //TODO AQUI HACEMOS EL GETSTRING PARA RECUPERAR EL COLOR!
        // Obtener el color guardado, "negro" es un valor por defecto en caso de que no haya nada guardado
        String colorGuardado = preferencia.getString("color", "negro");
        elegirColor(colorGuardado);
    }

    private void elegirColor(String color) {
        int colorRes; // Variable para el recurso de color
        switch (color.toLowerCase()) {
            case "rojo":
                colorRes = R.color.rojo;
                break;
            case "verde":
                colorRes = R.color.verde;
                break;
            case "azul":
                colorRes = R.color.azul;
                break;
            case "amarillo":
                colorRes = R.color.amarillo;
                break;
            case "negro": // Asegúrate de tener este caso si es tu color predeterminado
                colorRes = R.color.black;
                break;
            default:
                Toast.makeText(this, "No coincide con ningún color", Toast.LENGTH_SHORT).show();
                return; // Salir si no se encuentra el color
        }
        int colorFinal = ContextCompat.getColor(MainActivity.this, colorRes);
        tv_color.setBackgroundColor(colorFinal);
    }

}