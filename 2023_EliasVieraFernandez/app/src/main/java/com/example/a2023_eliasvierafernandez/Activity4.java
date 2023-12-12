package com.example.a2023_eliasvierafernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {

    private EditText et_peso;
    private TextView tv_imc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        et_peso = findViewById(R.id.et_peso);
        tv_imc = findViewById(R.id.tv_imc);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String mensajeRecibido = bundle.getString("mensaje");

        et_peso.setText(mensajeRecibido);



    }
}