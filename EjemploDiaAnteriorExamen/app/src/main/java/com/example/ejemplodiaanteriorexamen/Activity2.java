package com.example.ejemplodiaanteriorexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_recibido;
    private EditText et_devolver;
    private Button btn_devolver_mensaje, btn_vacio;
    private String datoRespuesta, mensajeRecibido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tv_recibido=findViewById(R.id.tv_recibido);
        btn_devolver_mensaje = findViewById(R.id.btn_devolver_mensaje);
        btn_vacio = findViewById(R.id.btn_vacio);
        et_devolver = findViewById(R.id.et_devolver);

        tv_recibido.setOnClickListener(this);
        btn_devolver_mensaje.setOnClickListener(this);
        btn_vacio.setOnClickListener(this);
        et_devolver.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mensajeRecibido = bundle.getString("envio");
        tv_recibido.setText(mensajeRecibido);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_vacio){
            Intent intentVacio = new Intent();
            setResult(RESULT_CANCELED, intentVacio);
            finish();
        }else if (v.getId() == R.id.btn_devolver_mensaje){

            String mensajeDevuelta = et_devolver.getText().toString();
            Intent intentDevolver = new Intent();
            datoRespuesta = "Me enviaste este mensaje: "+mensajeRecibido+ " y yo te devuelvo este mensaje: "+mensajeDevuelta;
            intentDevolver.putExtra("devolver", datoRespuesta);
            setResult(RESULT_OK, intentDevolver);
            finish();
        }

    }
}