package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_mensajeRecibido;
    private Button btn_devolver_info;
    private String mensajeRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        tv_mensajeRecibido = findViewById(R.id.tv_mensajeRecibido);
        btn_devolver_info = findViewById(R.id.btn_devolver_info);
        btn_devolver_info.setOnClickListener(this);
        tv_mensajeRecibido.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mensajeRecibido = bundle.getString("mensaje");

        tv_mensajeRecibido.setText(mensajeRecibido);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_devolver_info){
            String respuesta = "Este la informacion que me has enviado: "+mensajeRecibido;
            Intent intent = new Intent();
            intent.putExtra("respuesta", respuesta);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}