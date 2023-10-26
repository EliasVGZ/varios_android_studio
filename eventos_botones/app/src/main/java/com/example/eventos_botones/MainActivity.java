package com.example.eventos_botones;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvMensaje;

    private Button btnSi, btnNo, btnAveces, btnNose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMensaje = findViewById(R.id.tvMensaje);
        btnSi = findViewById(R.id.btnSi);//metodo 1
        btnNo=findViewById(R.id.btnNo);//metodo 2
        btnAveces = findViewById(R.id.btnAveces); //metodo 3
        btnNose = findViewById(R.id.btnNose); //metodo 4


        //paso 2-> INSTANCIAR EL OBJETO DE LA CLASE AUXILIAR(que implementó la interfaz) -> objeto escuchador

        Auxiliar escuchadorSi = new Auxiliar();

        //paso 3 -> asignar el escuchador a la vista correspondiente
        btnSi.setOnClickListener(escuchadorSi);
        btnNo.setOnClickListener(escuchadorNo);
        //ASIGNACION DEL BOTON NO SÉ
        btnNose.setOnClickListener(this);


        //METODO 3 -> Crear el listener y asignarlo a la view en una unica sentencia
        btnAveces.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMensaje.setText("haz clickeado a veces");
            }
        });







    }

    //METODO 2 -> Crear el objeto escuchador mediante una clase anónima
    private View.OnClickListener escuchadorNo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvMensaje.setText("has clickeado NOOOOOOOO");
        }
    };




    //METODO 4 -> Implementar la interfaz desde la MainActivity haciendo implement View.OnClickListener
    @Override
    public void onClick(View v) {
        tvMensaje.setText("haz pulsado el boton no sé");
    }


    // METODO 1 : Crear clase auxiliar para implementar la interfaz

    private class Auxiliar implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            tvMensaje.setText("has clickeado SI");
        }
    }


}