package com.example.preferenciascompartidas_colores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    String seleccion;
    private TextView tv_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        if (Objects.equals(seleccion, "rojo")){
                            // Cambiar el color de fondo del TextView
                            int colorRojo = ContextCompat.getColor(MainActivity.this, R.color.red);
                            tv_color.setBackgroundColor(colorRojo);
                            //TODO FALTA GUARDAR EL COLOR ROJO
                        }
                    }
                });
        ventana.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Has seleccionado el color: "+seleccion, Toast.LENGTH_SHORT).show();

            }
        });

        ventana.show();
    }
}